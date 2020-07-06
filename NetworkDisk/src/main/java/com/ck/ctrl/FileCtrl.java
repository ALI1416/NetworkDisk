package com.ck.ctrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.constant.Constant;
import com.ck.constant.UserType;
import com.ck.po.File;
import com.ck.po.Folder;
import com.ck.po.User;
import com.ck.result.Result;
import com.ck.result.ResultCode;
import com.ck.serv.FileServ;
import com.ck.serv.FolderServ;
import com.ck.serv.HistoryBrowsingServ;
import com.ck.serv.HistoryDownloadServ;
import com.ck.serv.TipOffServ;
import com.ck.serv.UserServ;
import com.ck.util.CompressPictureUtils;
import com.ck.util.ConversionUtils;
import com.ck.util.DateUtils;
import com.ck.util.FileUpDownLoadUtils;
import com.ck.util.FileUtils;
import com.ck.util.IpUtils;
import com.ck.util.ListUtils;
import com.ck.util.StringUtils;
import com.ck.util.ZipUtils;
import com.ck.vo.FileVo;
import com.ck.vo.HistoryBrowsingVo;
import com.ck.vo.HistoryDownloadVo;
import com.ck.vo.TipOffVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("file")
public class FileCtrl {
	@Autowired
	private UserServ userServer;
	@Autowired
	private FileServ fileServer;
	@Autowired
	private FolderServ folderServer;
	@Autowired
	private HistoryBrowsingServ historyBrowsingServer;
	@Autowired
	private HistoryDownloadServ historyDownloadServer;
	@Autowired
	private TipOffServ tipOffServer;

	private static List<String> textSuffix = Arrays.asList("txt", "text", "ini", "inf", "java", "php", "c", "cpp", "py",
			"html", "htm", "css", "js", "vbs", "jsp", "bat", "sql");
	private static List<String> imageSuffix = Arrays.asList("png", "jpg", "jpeg", "bmp", "gif");

	/**
	 * 用户信息
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/user/info/{userId}")
	public Result userInfo(@PathVariable Integer userId) {
		User user = userServer.findById(userId);
		if (user == null || user.getType() == UserType.DELETE) {
			return Result.e(ResultCode.USER_NOT_EXIST);// 用户不存在
		}
		/* 用户信息 */
		User u = new User();
		u.setId(user.getId());
		u.setName(user.getName());
		u.setGender(user.getGender());
		u.setYear(user.getYear());
		u.setDescription(user.getDescription());
		return Result.ok(u);
	}

	/**
	 * 用户的文件信息
	 * 
	 * @param session
	 * @param userId
	 * @return
	 */
	@PostMapping("/user/file/info/{userId}")
	public Result userFileInfo(HttpSession session, @PathVariable Integer userId) {
		User user = userServer.findById(userId);
		if (user == null || user.getType() == UserType.DELETE) {
			return Result.e(ResultCode.USER_NOT_EXIST);// 用户不存在
		}
		/* 获取文件信息 */
		FileVo f = new FileVo();
		f.setUserid(userId);
		f.setIsdelete(0);
		f.setIssubfile(0);
		f.setIsshare(1);
		f.setIssearch(1);
		String orderBy = "isfolder DESC, name";
		PageHelper.orderBy(orderBy);
		List<File> files = fileServer.find(f);
		Integer id = (Integer) session.getAttribute("id");
		/* 设置文件信息 */
		for (File ff : files) {
			if (ff.getIslimit() == 1 && id == null) {// 需要登录
				ff.setUuid("");
			}
			if (ff.getPwd().length() != 0) {// 有密码
				ff.setPwd("1");
				ff.setUuid("");
			}
		}
		User u = new User();
		u.setId(user.getId());
		u.setName(user.getName());
		u.setFiles(files);
		return Result.ok(u);
	}

	/**
	 * 文件信息
	 * 
	 * @param request
	 * @param session
	 * @param timestamp
	 * @param folderTimestamp
	 * @param folderName
	 * @param pwd
	 * @return
	 */
	@PostMapping("/file/info/{timestamp}")
	public Result fileInfo(HttpServletRequest request, HttpSession session, @PathVariable String timestamp,
			String folderTimestamp, String folderName, String pwd) {
		/* 查询文件信息 */
		File file = fileServer.findByTimestamp(timestamp);
		if (file == null || file.getIsdelete() == 1 || file.getIsfolder() == 1) {
			return Result.e(ResultCode.FILE_NOT_EXIST);// 文件不存在
		}

		Integer id = (Integer) session.getAttribute("id");
		/* 判断密码是否正确 如果输入了密码 */
		if (pwd != null) {
			if (file.getPwd().equals(pwd)) {
				return Result.ok(file);
			} else {
				return Result.e(ResultCode.FILE_PASSWORD_ERROR);// 密码错误
			}
		} else {
			/* 浏览次数+1 */
			addBrowsing(request, id, file.getId());
		}
		/* 获取用户信息 */
		User user = userServer.findById(file.getUserid());
		if (user.getType() == UserType.DELETE) {
			return Result.e(ResultCode.FILE_NOT_EXIST);// 用户已注销
		}
		/* 设置文件信息 */
		if (file.getPwd().length() != 0) {// 需要密码
			file.setPwd("1");
			file.setUuid("");
		}
		if (file.getIslimit() == 1 && id == null) {// 需要登录
			file.setUuid("");
		}
		/* 设置用户信息 */
		User u = new User();
		u.setId(user.getId());
		u.setName(user.getName());
		/* 设置文件夹信息 如果有 */
		if (folderTimestamp != null) {
			File folderInfo = new File();
			folderInfo.setTimestamp(folderTimestamp);
			folderInfo.setName(folderName);
			u.setFolderInfo(folderInfo);
		}
		/* 设置文件信息 */
		u.setFile(file);
		return Result.ok(u);
	}

	/**
	 * 文件夹里的文件信息
	 * 
	 * @param request
	 * @param session
	 * @param timestamp
	 * @param pwd
	 * @return
	 */
	@PostMapping("/folder/file/info/{timestamp}")
	public Result fileFolderFileInfo(HttpServletRequest request, HttpSession session, @PathVariable String timestamp,
			String pwd) {
		/* 查询文件信息 */
		File file = fileServer.findByTimestamp(timestamp);
		if (file == null || file.getIsdelete() == 1 || file.getIsfolder() == 0) {
			return Result.e(ResultCode.FILE_NOT_EXIST);// 文件不存在
		}
		/* 获取用户信息 */
		User user = userServer.findById(file.getUserid());
		if (user.getType() == UserType.DELETE) {
			return Result.e(ResultCode.FILE_NOT_EXIST);// 用户已注销
		}
		/* 设置用户信息 */
		User u = new User();
		u.setId(user.getId());
		u.setName(user.getName());
		u.setFolderInfo(file);
		/* 如果文件夹需要登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (file.getIslimit() == 1 && id == null) {
			return Result.e(ResultCode.FILE_HAS_LIMITED, u);// 需要登录
		}
		/* 如果文件夹有密码 */
		if (file.getPwd().length() != 0) {
			if (pwd == null) {
				return Result.e(ResultCode.FILE_HAS_PASSWORD, u);// 文件夹需要密码
			}
			if (!file.getPwd().equals(pwd)) {
				return Result.e(ResultCode.FILE_PASSWORD_ERROR, u);// 密码错误
			}
		}
		/* 初始化文件列表 */
		List<File> files = new ArrayList<File>();
		/* 设置文件列表 */
		List<Folder> folders = folderServer.findByFolderId(file.getId());
		for (Folder folder : folders) {
			File f = fileServer.findById(folder.getFileid());
			if (f.getIsdelete() == 0 || f.getIssearch() == 0) {
				if (f.getIslimit() == 1 && id == null) {// 需要登录
					f.setUuid("");
				}
				if (f.getPwd().length() != 0) {// 有密码
					f.setPwd("1");
					f.setUuid("");
				}
				files.add(f);
			}
		}
		if (files.size() == 0) {
			return Result.e(ResultCode.FILE_IS_BLANK, u);// 文件为空
		}
		ListUtils.sort(files, true, "name");// 按照文件名排序
		u.setFiles(files);
		return Result.ok(u);
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param session
	 * @param userAgent
	 * @param uuid
	 * @param id
	 * @return
	 */
	@GetMapping("/download/{uuid}")
	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpSession session,
			@RequestHeader("User-Agent") String userAgent, @PathVariable String uuid, Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (uid != null) {
			id = uid;
		} else if (id == null) {
			id = UserType.ROOT;
		}
		/* 查找文件 */
		File file1 = fileServer.findByUuid(uuid);
		if (file1 == null || file1.getIsfolder() == 1) {
			return FileUpDownLoadUtils.notFound();
		}
		/* 下载次数+1 */
		addDownload(request, id, file1.getId());
		/* 返回文件信息 */
		return FileUpDownLoadUtils.download(request, userAgent, Constant.FILE_SAVE_PATH + file1.getPath(),
				file1.getName());
	}

	/**
	 * 下载文件夹
	 * 
	 * @param request
	 * @param session
	 * @param userAgent
	 * @param uuid
	 * @return
	 */
	@PostMapping("/downloadFolder/{uuid}")
	public Result downloadFolder(HttpServletRequest request, HttpSession session,
			@RequestHeader("User-Agent") String userAgent, @PathVariable String uuid) {
		/* 查找文件 */
		File file1 = fileServer.findByUuid(uuid);
		if (file1 == null || file1.getIsfolder() == 0) {
			return Result.e(ResultCode.FILE_IS_BLANK);
		}
		List<Folder> folders = folderServer.findByFolderId(file1.getId());
		if (folders == null) {
			return Result.e(ResultCode.FILE_IS_BLANK);
		}
		/* 设置迅雷下载文本 */
		String link = "----------------------------------------------------------------------------------------------------\n文件过大，请复制本页文字，到迅雷下载。\n----------------------------------------------------------------------------------------------------\n";
		String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/file/download/";
		Integer uid = (Integer) session.getAttribute("id");
		String userid = (uid == null ? "\n" : ("?id=" + uid + "\n"));
		Long size = 0L;
		List<String> filePath = new ArrayList<String>();
		List<String> newFileName = new ArrayList<String>();
		/* 判断是否为文件夹所有者 */
		Integer id = (Integer) session.getAttribute("id");
		/* 遍历folder */
		for (Folder folder : folders) {
			File file = fileServer.findById(folder.getFileid());
			if ((file.getIsdelete() == 0)// 未删除
					&& ((file1.getUserid().equals(id)) // 是本人
							|| (UserType.ADMIN.equals(id))// 是管理员
							|| (file.getPwd().length() == 0 && file.getIslimit() == 0)// 没有密码 没有限制
							|| (file.getIslimit() == 1 && id != null))) {// 有限制 已登录
				size += file.getLength();
				filePath.add(Constant.FILE_SAVE_PATH + file.getPath());
				newFileName.add(file.getName());
				link += basePath + file.getUuid() + userid;
			}
		}
		if (filePath.size() == 0) {
			return Result.e(ResultCode.FILE_IS_BLANK);
		}
		if (size > Constant.PACKAGE_DOWMLOAD_MAX) {// 大于256MB，到迅雷下载
			return Result.e(ResultCode.FILE_IS_TOO_LARGE, link);
		}
		/* 压缩文件 */
		long timestamp = DateUtils.getSyncTimestamp();
		String zipPath = Constant.FILE_TEMP_PATH + timestamp;
		ZipUtils.zipFiles(filePath, newFileName, zipPath);
		return Result.ok(timestamp);
	}

	/**
	 * 打包下载
	 * 
	 * @param request
	 * @param session
	 * @param uuids
	 * @return
	 */
	@PostMapping("/downloadPackage")
	public Result downloadPackage(HttpServletRequest request, HttpSession session, @RequestBody List<String> uuids) {
		if (uuids == null || uuids.size() == 0) {
			return Result.e(ResultCode.FILE_IS_BLANK);
		}
		/* 设置迅雷下载文本 */
		String link = "----------------------------------------------------------------------------------------------------\n文件过大，请复制本页文字，到迅雷下载。\n----------------------------------------------------------------------------------------------------\n";
		String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/file/download/";
		Integer uid = (Integer) session.getAttribute("id");
		String userid = (uid == null ? "\n" : ("?id=" + uid + "\n"));
		Long size = 0L;
		List<String> filePath = new ArrayList<String>();
		List<String> newFileName = new ArrayList<String>();
		/* 遍历uuid */
		for (String uuid : uuids) {
			if (uuid.length() != 0) {
				File file = fileServer.findByUuid(uuid);
				if (file != null && file.getIsfolder() == 0) {
					size += file.getLength();
					filePath.add(Constant.FILE_SAVE_PATH + file.getPath());
					newFileName.add(file.getName());
					link += basePath + file.getUuid() + userid;
				}
			}
		}
		if (filePath.size() == 0) {
			return Result.e(ResultCode.FILE_IS_BLANK);
		}
		if (size > Constant.PACKAGE_DOWMLOAD_MAX) {// 大于256MB，到迅雷下载
			return Result.e(ResultCode.FILE_IS_TOO_LARGE, link);
		}
		/* 压缩文件 */
		long timestamp = DateUtils.getSyncTimestamp();
		String zipPath = Constant.FILE_TEMP_PATH + timestamp;
		ZipUtils.zipFiles(filePath, newFileName, zipPath);
		return Result.ok(timestamp);
	}

	/**
	 * 打包下载
	 * 
	 * @param request
	 * @param userAgent
	 * @param timestamp
	 * @return
	 */
	@GetMapping("/downloadPackage/{timestamp}")
	public ResponseEntity<byte[]> downloadPackage(HttpServletRequest request,
			@RequestHeader("User-Agent") String userAgent, @PathVariable String timestamp) {
		String path = Constant.FILE_TEMP_PATH + timestamp;
		String newFileName = "【打包下载】" + timestamp + ".zip";
		return FileUpDownLoadUtils.download(request, userAgent, path, newFileName);
	}

	/**
	 * 预览文件
	 * 
	 * @param uuid
	 * @return
	 */
	@GetMapping("/preview/{uuid}")
	public ResponseEntity<byte[]> filePreview(@PathVariable String uuid) {
		/* 查找文件 */
		File file = fileServer.findByUuid(uuid);
		if (file == null || file.getIsfolder() == 1) {
			return FileUpDownLoadUtils.notFound();
		}
		/* 不同类型后缀返回不同预览结果 */
		String suffit = StringUtils.getSuffix(file.getName());
		if (textSuffix.contains(suffit.toLowerCase())) {// 文本
			if (!new java.io.File(Constant.FILE_TEMP_PATH + file.getPath()).exists()) {// 临时文件不存在
				FileUtils.saveAsUTF8LimitSize(Constant.FILE_SAVE_PATH + file.getPath(),
						Constant.FILE_TEMP_PATH + file.getPath(), Constant.TEXT_PREVIEW_MAX);// 不超过32KB
			}
			return FileUpDownLoadUtils.text(Constant.FILE_TEMP_PATH + file.getPath());
		}
		if (imageSuffix.contains(suffit.toLowerCase())) {// 图片
			if (!new java.io.File(Constant.FILE_TEMP_PATH + file.getPath()).exists()) {// 临时文件不存在
				CompressPictureUtils.CompressPicture(Constant.FILE_SAVE_PATH + file.getPath(),
						Constant.FILE_TEMP_PATH + file.getPath());
			}
			return FileUpDownLoadUtils.jpeg(Constant.FILE_TEMP_PATH + file.getPath());
		}
		return FileUpDownLoadUtils.notSupport();// 不支持预览
	}

	/**
	 * 搜索文件
	 * 
	 * @param pages
	 * @param rows
	 * @param name
	 * @return
	 */
	@PostMapping("/search")
	public Result fileSearch(HttpSession session, @RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, String name) {
		Integer id = (Integer) session.getAttribute("id");
		/* 查询文件信息 */
		FileVo fv = new FileVo();
		fv.setIsdelete(0);
		fv.setIsshare(1);
		fv.setIssearch(1);
		fv.setNameindex(StringUtils.getAnsj(name));
		PageHelper.startPage(pages, rows);
		List<File> files = fileServer.find(fv);
		/* 设置文件信息 */
		for (File ff : files) {
			if (ff.getIslimit() == 1 && id == null) {// 需要登录
				ff.setUuid("");
			}
			if (ff.getPwd().length() != 0) {// 有密码
				ff.setPwd("1");
				ff.setUuid("");
			}
		}
		PageInfo<File> pageInfo = new PageInfo<File>(files);
		return Result.ok(pageInfo);
	}

	/**
	 * 举报
	 * 
	 * @param request
	 * @param session
	 * @param tipOff
	 * @return
	 */
	@PostMapping("/tipOff")
	public Result fileTipOff(HttpServletRequest request, HttpSession session, @RequestBody TipOffVo tipOff) {
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			tipOff.setUserid(UserType.ROOT);
		} else {
			tipOff.setUserid(id);
		}
		tipOff.setIp(ConversionUtils.ip2Long(IpUtils.getIp(request)));
		tipOff.setTime(DateUtils.getIntTimestamp());
		tipOffServer.insert(tipOff);
		return Result.ok();
	}

	/**
	 * 浏览次数+1
	 * 
	 * @param request
	 * @param userId
	 * @param fileId
	 */
	public void addBrowsing(HttpServletRequest request, Integer userId, Integer fileId) {
		fileServer.addBrowsing(fileId);
		HistoryBrowsingVo historyBrowsing = new HistoryBrowsingVo();
		if (userId == null) {
			historyBrowsing.setUserid(UserType.ROOT);
		} else {
			historyBrowsing.setUserid(userId);
		}
		historyBrowsing.setFileid(fileId);
		historyBrowsing.setIp(ConversionUtils.ip2Long(IpUtils.getIp(request)));
		historyBrowsing.setTime(DateUtils.getIntTimestamp());
		historyBrowsingServer.insert(historyBrowsing);
	}

	/**
	 * 下载次数+1
	 * 
	 * @param request
	 * @param userId
	 * @param fileId
	 */
	public void addDownload(HttpServletRequest request, Integer userId, Integer fileId) {
		fileServer.addDownload(fileId);
		HistoryDownloadVo historyDownload = new HistoryDownloadVo();
		if (userId == null) {
			historyDownload.setUserid(UserType.ROOT);
		} else {
			historyDownload.setUserid(userId);
		}
		historyDownload.setFileid(fileId);
		historyDownload.setIp(ConversionUtils.ip2Long(IpUtils.getIp(request)));
		historyDownload.setTime(DateUtils.getIntTimestamp());
		historyDownloadServer.insert(historyDownload);
	}

}
