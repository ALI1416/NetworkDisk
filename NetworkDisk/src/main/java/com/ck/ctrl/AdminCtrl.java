package com.ck.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ck.constant.Constant;
import com.ck.constant.TipOffStatus;
import com.ck.constant.UserType;
import com.ck.po.File;
import com.ck.po.Folder;
import com.ck.po.HistoryBrowsing;
import com.ck.po.HistoryDownload;
import com.ck.po.TipOff;
import com.ck.po.User;
import com.ck.po.UserInfo;
import com.ck.result.Result;
import com.ck.result.ResultCode;
import com.ck.serv.FileServ;
import com.ck.serv.FolderServ;
import com.ck.serv.HistoryBrowsingServ;
import com.ck.serv.HistoryDownloadServ;
import com.ck.serv.TipOffServ;
import com.ck.serv.UserInfoServ;
import com.ck.serv.UserServ;
import com.ck.util.DateUtils;
import com.ck.util.FileUpDownLoadUtils;
import com.ck.util.StringUtils;
import com.ck.util.UserInfoCSVUtils;
import com.ck.vo.FileVo;
import com.ck.vo.HistoryBrowsingVo;
import com.ck.vo.HistoryDownloadVo;
import com.ck.vo.TipOffVo;
import com.ck.vo.UserInfoVo;
import com.ck.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("admin")
public class AdminCtrl {
	@Autowired
	private UserInfoServ userInfoServer;
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

	/**
	 * 更新文件信息
	 * 
	 * @param session
	 * @param file
	 * @return
	 */
	@PostMapping("/edit/file")
	public Result editFile(HttpSession session, @RequestBody FileVo file) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		if (file.getName() != null && file.getName().length() != 0) {
			String nameindex = StringUtils.getAnsj(file.getName());
			file.setNameindex(nameindex);
		}
		fileServer.update(file);
		return Result.ok();
	}

	/**
	 * 更新用户信息
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/edit/user")
	public Result editUser(HttpSession session, @RequestBody UserVo user) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		if (UserType.DELETE.equals(user.getId()) || UserType.ROOT.equals(user.getId())) {// 不可修改id为0或1
			return Result.ok();
		}
		if (UserType.ADMIN.equals(user.getId())) {// id是管理员
			if (!UserType.ADMIN.equals(user.getType())) {// 管理员用户权限不可修改
				user.setType(null);
			}
		} else if (UserType.ROOT.equals(user.getType()) || UserType.ADMIN.equals(user.getType())) {// 其他用户不可设置类型为ROOT或者ADMIN
			user.setType(null);
		}
		userServer.update(user);
		return Result.ok();
	}

	/**
	 * 更新用户实名信息
	 * 
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@PostMapping("/edit/realname")
	public Result editRealname(HttpSession session, @RequestBody UserInfoVo userinfo) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		userInfoServer.update(userinfo);
		return Result.ok();
	}

	/**
	 * 更新举报信息
	 * 
	 * @param session
	 * @param tipOff
	 * @return
	 */
	@PostMapping("/edit/tipOff")
	public Result editTipOff(HttpSession session, @RequestBody TipOffVo tipOff) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		TipOff t = tipOffServer.findById(tipOff.getId());
		FileVo fv = new FileVo();
		fv.setId(t.getFileid());
		if (TipOffStatus.ILLEGAL.equals(tipOff.getStatus())) {// 违法文件，删除
			fv.setIsdelete(1);
		} else {// 未违法，不删除
			fv.setIsdelete(0);
		}
		fileServer.update(fv);
		tipOffServer.update(tipOff);
		return Result.ok();
	}

	/**
	 * 查询文件信息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param file
	 * @return
	 */
	@PostMapping("/fileAll")
	public Result fileAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session, @RequestBody FileVo file) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		if (file.getName() != null && file.getName().length() != 0) {
			String nameindex = StringUtils.getAnsj(file.getName());
			file.setNameindex(nameindex);
		}
		String orderBy = file.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<File> f = fileServer.find(file);
		PageInfo<File> pageInfo = new PageInfo<File>(f);
		return Result.ok(pageInfo);
	}

	/**
	 * 查询一个文件的信息
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@PostMapping("/file/{id}")
	public Result file(HttpSession session, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		File file = fileServer.findById(id);
		return Result.ok(file);
	}

	/**
	 * 查询文件夹内文件信息
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@PostMapping("/folder/{id}")
	public Result fileFolder(HttpSession session, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		/* 初始化文件列表 */
		List<File> files = new ArrayList<File>();
		/* 设置文件列表 */
		List<Folder> folders = folderServer.findByFolderId(id);
		for (Folder folder : folders) {
			File f = fileServer.findById(folder.getFileid());
			files.add(f);
		}
		File folderInfo = fileServer.findById(id);
		User user = userServer.findById(folderInfo.getUserid());
		user.setFolderInfo(folderInfo);
		user.setFiles(files);
		return Result.ok(user);
	}

	/**
	 * 查询用户信息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/userAll")
	public Result userAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session, @RequestBody UserVo user) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String orderBy = user.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<User> u = userServer.find(user);
		PageInfo<User> pageInfo = new PageInfo<User>(u);
		return Result.ok(pageInfo);
	}

	/**
	 * 查询一个用户的信息
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@PostMapping("/user/{id}")
	public Result user(HttpSession session, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		User user = userServer.findById(id);
		user.setPwd("");
		UserInfo userInfo = userInfoServer.findById(user.getUserinfoid());
		user.setUserInfo(userInfo);
		return Result.ok(user);
	}

	/**
	 * 查询用户信息通过实名ID
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@PostMapping("/userByRealnameId/{id}")
	public Result userByRealnameId(HttpSession session, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		User user = userServer.findByUserinfoid(id);
		if (user != null) {
			user.setPwd("");
		}
		return Result.ok(user);
	}

	/**
	 * 查询用户实名信息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param userinfo
	 * @return
	 */
	@PostMapping("/realnameAll")
	public Result realnameAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session, @RequestBody UserInfoVo userinfo) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!uid.equals(UserType.ADMIN)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String orderBy = userinfo.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<UserInfo> u = userInfoServer.find(userinfo);
		PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(u);
		return Result.ok(pageInfo);
	}

	/**
	 * 查询一个用户的实名信息
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	@PostMapping("/realname/{id}")
	public Result realname(HttpSession session, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		UserInfo userInfo = userInfoServer.findById(id);
		return Result.ok(userInfo);
	}

	/**
	 * 查询浏览记录信息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param historyBrowsing
	 * @return
	 */
	@PostMapping("/historyBrowsingAll")
	public Result historyBrowsingAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session,
			@RequestBody HistoryBrowsingVo historyBrowsing) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String orderBy = historyBrowsing.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<HistoryBrowsing> h = historyBrowsingServer.find(historyBrowsing);
		PageInfo<HistoryBrowsing> pageInfo = new PageInfo<HistoryBrowsing>(h);
		return Result.ok(pageInfo);
	}

	/**
	 * 查询下载记录信息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param historyDownload
	 * @return
	 */
	@PostMapping("/historyDownloadAll")
	public Result historyDownloadAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session,
			@RequestBody HistoryDownloadVo historyDownload) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String orderBy = historyDownload.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<HistoryDownload> h = historyDownloadServer.find(historyDownload);
		PageInfo<HistoryDownload> pageInfo = new PageInfo<HistoryDownload>(h);
		return Result.ok(pageInfo);
	}

	/**
	 * 查询举报记录消息
	 * 
	 * @param pages
	 * @param rows
	 * @param session
	 * @param tipOff
	 * @return
	 */
	@PostMapping("/tipOffAll")
	public Result tipOffAll(@RequestParam(defaultValue = "1") Integer pages,
			@RequestParam(defaultValue = "20") Integer rows, HttpSession session, @RequestBody TipOffVo tipOff) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String orderBy = tipOff.getOrderBy();
		if (orderBy == null || orderBy.length() == 0) {
			PageHelper.startPage(pages, rows);
		} else {
			PageHelper.startPage(pages, rows, orderBy);
		}
		List<TipOff> h = tipOffServer.find(tipOff);
		PageInfo<TipOff> pageInfo = new PageInfo<TipOff>(h);
		return Result.ok(pageInfo);
	}

	@PostMapping("/info/{type}/{id}")
	public Result info(HttpSession session, @PathVariable String type, @PathVariable Integer id) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		UserInfo userInfo = new UserInfo();
		User user = new User();
		File file = null;
		List<File> files = null;
		List<HistoryDownload> historyDownload = null;
		List<HistoryBrowsing> historyBrowsing = null;
		if (type.equals("realname")) {// 实名信息
			userInfo = userInfoServer.findById(id);
			if (userInfo == null) {
				return Result.e(ResultCode.USER_NOT_EXIST);
			}
			user = userServer.findByUserinfoid(id);
			if (user == null) {// 没有注册
				user = new User();
				user.setUserInfo(userInfo);
				return Result.ok(user);
			}
			files = fileServer.findByUserId(user.getId());
			if (files == null || files.size() == 0) {// 没有文件
				user.setPwd("");
				user.setUserInfo(userInfo);
				return Result.ok(user);
			}
			file = files.get(0);
		} else if (type.equals("user")) {// 用户
			user = userServer.findById(id);
			if (user == null) {
				return Result.e(ResultCode.USER_NOT_EXIST);
			}
			userInfo = userInfoServer.findById(user.getUserinfoid());
			files = fileServer.findByUserId(id);
			if (files == null || files.size() == 0) {// 没有文件
				user.setPwd("");
				user.setUserInfo(userInfo);
				return Result.ok(user);
			}
			file = files.get(0);
		} else if (type.equals("file")) {// 文件
			file = fileServer.findById(id);
			if (file == null) {
				return Result.e(ResultCode.USER_NOT_EXIST);
			}
			user = userServer.findById(file.getUserid());
			userInfo = userInfoServer.findById(user.getUserinfoid());
			files = fileServer.findByUserId(user.getId());
		} else if (type.equals("f")) {// 文件2
			file = fileServer.findById(id);
			if (file == null) {
				return Result.e(ResultCode.USER_NOT_EXIST);
			}
		} else {
			return Result.e(ResultCode.PARAM_IS_ERROR);
		}
		historyDownload = historyDownloadServer.findByFileId(file.getId());
		historyBrowsing = historyBrowsingServer.findByFileId(file.getId());
		user.setPwd("");
		user.setUserInfo(userInfo);
		user.setFile(file);
		user.setFiles(files);
		user.setHistoryBrowsing(historyBrowsing);
		user.setHistoryDownload(historyDownload);
		return Result.ok(user);
	}

	@PostMapping("/realname/upload")
	public Result realnameUpload(HttpSession session, MultipartFile file) {
		Integer uid = (Integer) session.getAttribute("id");
		if (!UserType.ADMIN.equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		String timestamp = String.valueOf(DateUtils.getSyncTimestamp());
		FileUpDownLoadUtils.uploadReAll(Constant.FILE_SAVE_PATH, timestamp, file);
		List<UserInfoVo> list = UserInfoCSVUtils.read(Constant.FILE_SAVE_PATH + timestamp);
		for (UserInfoVo u : list) {
			userInfoServer.insert(u);
		}
		return Result.ok();
	}
}
