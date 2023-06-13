package com.ck.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ck.constant.Constant;
import com.ck.constant.UserType;
import com.ck.po.File;
import com.ck.po.Folder;
import com.ck.po.User;
import com.ck.po.UserInfo;
import com.ck.result.Result;
import com.ck.result.ResultCode;
import com.ck.serv.FileServ;
import com.ck.serv.FolderServ;
import com.ck.serv.UserInfoServ;
import com.ck.serv.UserServ;
import com.ck.util.BCryptUtils;
import com.ck.util.ConversionUtils;
import com.ck.util.DateUtils;
import com.ck.util.FileUpDownLoadUtils;
import com.ck.util.IdCardUtils;
import com.ck.util.ListUtils;
import com.ck.util.StringUtils;
import com.ck.vo.FileVo;
import com.ck.vo.FolderVo;
import com.ck.vo.UserInfoVo;
import com.ck.vo.UserVo;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("user")
public class UserCtrl {
	@Autowired
	private UserInfoServ userInfoServer;
	@Autowired
	private UserServ userServer;
	@Autowired
	private FileServ fileServer;
	@Autowired
	private FolderServ folderServer;

	/* 正则表达式 */
	private String regAccount = "^[a-zA-Z][0-9a-zA-Z]{2,15}$";
	private String regLoginAccount = "^[0-9a-zA-Z]{3,18}$";
	private String regPwd = "^[0-9a-f]{32}$";// MD5加密过的密码
	private String regName = "^[\\u4e00-\\u9fa5]{2,8}$";
	private String regNumber = "^[1-9][0-9]{8}[0-9z][0-9]{3}$";

	/**
	 * 登录
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public Result userLogin(HttpSession session, @RequestBody UserVo user) {
		String account = user.getAccount();
		String pwd = user.getPwd();
		/* 判断输入是否合法 */
		if (account == null || pwd == null) {// 用户名或密码为空
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regLoginAccount, account) || !Pattern.matches(regPwd, pwd)) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		/* 判断登录类型 */
		User ok = null;
		if (Pattern.matches(regNumber, account)) {// 学号登录
			UserInfo userInfo = userInfoServer.findByNumber(account);
			if (userInfo != null) {
				ok = userServer.findByUserinfoid(userInfo.getId());
			}
		} else if (Pattern.matches(regAccount, account)) {// 用户名登录
			ok = userServer.findByAccount(account);
		} else if (IdCardUtils.regIdCard(account)) {// 身份证号码登录
			UserInfo userInfo = userInfoServer.findByIdcard(account);
			if (userInfo != null) {
				ok = userServer.findByUserinfoid(userInfo.getId());
			}
		}
		/* 判断密码是否正确 */
		if (ok == null || !BCryptUtils.matches(pwd, ok.getPwd())) {
			return Result.e(ResultCode.USER_LOGIN_ERROR);// 密码错误
		}
		/* 判断账号是否可用 */
		if (ok.getType().equals(UserType.DELETE) || ok.getType().equals(UserType.ROOT)) {// 账号已被删除或是ROOT用户
			return Result.e(ResultCode.USER_ACCOUNT_FORBIDDEN);// 账号被禁用
		}
		/* 把id和account保存到session */
		session.setAttribute("id", ok.getId());
		session.setAttribute("account", ok.getAccount());
		return Result.ok();
	}

	/**
	 * 注册
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public Result userRegister(HttpSession session, @RequestBody UserVo user) {
		/* 判断输入是否合法 */
		if (user.getAccount() == null || user.getPwd() == null) {
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regAccount, user.getAccount()) || !Pattern.matches(regPwd, user.getPwd())) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		UserInfoVo userInfo = user.getUserInfoVo();
		if (userInfo.getName() == null || userInfo.getNumber() == null || userInfo.getIdcard() == null) {
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regName, userInfo.getName()) || !Pattern.matches(regNumber, userInfo.getNumber())
				|| !IdCardUtils.regIdCard(userInfo.getIdcard())) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		/* 判断实名信息是否匹配，是否已注册 */
		UserInfo ok = userInfoServer.check(userInfo);
		if (ok == null) {
			return Result.e(ResultCode.USER_CERTIFICATION_ERROR);// 实名认证信息不匹配
		}
		User u = userServer.findByUserinfoid(ok.getId());
		if (u != null) {
			return Result.e(ResultCode.USER_CERTIFICATION_HAS_EXISTED);// 已存在实名认证
		}
		/* 导入其他数据 */
		user.setUserinfoid(ok.getId());
		user.setPwd(BCryptUtils.encode(user.getPwd()));
		user.setTime(DateUtils.getIntTimestamp());
		Integer type = ok.getType();
		user.setType(type);
		if (type.equals(UserType.STUDENT)) {
			user.setSizeall(Constant.STUDENT_SIZE_ALL);
			user.setSizeone(Constant.STUDENT_SIZE_ONE);
		} else if (type.equals(UserType.TEACHER)) {
			user.setSizeall(Constant.TEACHER_SIZE_ALL);
			user.setSizeone(Constant.TEACHER_SIZE_ONE);
		} else {
			user.setSizeall(0);
			user.setSizeone(0);
		}
		/* 保存用户 */
		Integer ok2 = userServer.insert(user);
		if (ok2 == 0) {
			return Result.e(ResultCode.USER_HAS_EXISTED);// 用户已存在
		}
		UserVo uv = new UserVo();
		uv.setId(user.getId());
		uv.setName("用户" + user.getId());
		Integer ok3 = userServer.update(uv);
		if (ok3 == 0) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
		/* 把id和account保存到session */
		session.setAttribute("id", user.getId());
		session.setAttribute("account", user.getAccount());
		return Result.ok();
	}

	/**
	 * 找回密码
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/find")
	public Result userFind(HttpSession session, @RequestBody UserVo user) {
		/* 判断输入是否合法 */
		if (user.getPwd() == null) {
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regPwd, user.getPwd())) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		UserInfoVo userInfo = user.getUserInfoVo();
		if (userInfo.getName() == null || userInfo.getNumber() == null || userInfo.getIdcard() == null) {
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regName, userInfo.getName()) || !Pattern.matches(regNumber, userInfo.getNumber())
				|| !IdCardUtils.regIdCard(userInfo.getIdcard())) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		/* 判断实名信息是否匹配 */
		UserInfo ok = userInfoServer.check(userInfo);
		if (ok == null) {
			return Result.e(ResultCode.USER_CERTIFICATION_ERROR);// 实名认证信息不匹配
		}
		/* 找到用户，修改密码 */
		User u = userServer.findByUserinfoid(ok.getId());
		if (u == null) {
			return Result.e(ResultCode.USER_CERTIFICATION_ERROR);// 未注册
		}
		UserVo uv = new UserVo();
		uv.setId(u.getId());
		uv.setPwd(BCryptUtils.encode(user.getPwd()));
		Integer ok2 = userServer.update(uv);
		if (ok2 == 0) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
		/* 把id和account保存到session */
		session.setAttribute("id", u.getId());
		session.setAttribute("account", u.getAccount());
		return Result.ok();
	}

	/**
	 * 修改密码
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/changePwd")
	public Result userChangePwd(HttpSession session, @RequestBody UserVo user) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 判断输入是否合法 */
		if (user.getPwd() == null || user.getNewPwd() == null) {
			return Result.e(ResultCode.PARAM_NOT_COMPLETE);// 参数缺失
		}
		if (!Pattern.matches(regPwd, user.getPwd()) || !Pattern.matches(regPwd, user.getNewPwd())) {
			return Result.e(ResultCode.PARAM_IS_ERROR);// 参数错误
		}
		/* 查询用户信息，判断密码是否正确 */
		User ok = userServer.findById(id);
		if (!BCryptUtils.matches(user.getPwd(), ok.getPwd())) {
			return Result.e(ResultCode.USER_LOGIN_ERROR);// 密码错误
		}
		/* 修改密码 */
		UserVo uv = new UserVo();
		uv.setId(ok.getId());
		uv.setPwd(BCryptUtils.encode(user.getNewPwd()));
		Integer ok2 = userServer.update(uv);
		if (ok2 == 0) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
		return Result.ok();
	}

	/**
	 * 查看个人信息
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/info")
	public Result userInfo(HttpSession session) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 查询用户信息 */
		User user = userServer.findById(id);
		user.setPwd("");
		UserInfo userInfo = userInfoServer.findById(user.getUserinfoid());
		user.setUserInfo(userInfo);
		return Result.ok(user);
	}

	/**
	 * 编辑个人信息
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@PostMapping("/edit")
	public Result userEdit(HttpSession session, @RequestBody UserVo user) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 修改用户信息 */
		UserVo u = new UserVo();
		u.setId(id);
		u.setName(user.getName());
		u.setGender(user.getGender());
		u.setYear(user.getYear());
		u.setDescription(user.getDescription());
		Integer ok = userServer.update(u);
		if (ok == 0) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
		return Result.ok();
	}

	/**
	 * 所有文件信息
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/user/file/info")
	public Result fileInfoAll(HttpSession session, String orderBy) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		User user = new User();
		/* 查询用户信息 */
		User u = userServer.findById(id);
		user.setSizeone(u.getSizeone());
		/* 查询文件信息 */
		FileVo f = new FileVo();
		f.setUserid(id);
		f.setIsdelete(0);
		f.setIssubfile(0);
		if (orderBy == null || orderBy.length() == 0) {
			orderBy = "isfolder DESC, name";
		}
		PageHelper.orderBy(orderBy);
		List<File> files = fileServer.find(f);
		user.setFiles(files);
		return Result.ok(user);
	}

	/**
	 * 文件信息
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/file/info/{timestamp}")
	public Result fileInfo(HttpSession session, @PathVariable String timestamp, String folderTimestamp,
			String folderName) {
		/* 判断用户是否登录 */
		Integer uid = (Integer) session.getAttribute("id");
		if (uid == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 查询文件信息 */
		File file = fileServer.findByTimestamp(timestamp);
		if (file == null) {
			return Result.e(ResultCode.FILE_IS_BLANK);// 文件为空
		}
		if (!file.getUserid().equals(uid)) {
			return Result.e(ResultCode.INVALID_OPERATION);
		}
		if (file.getIsdelete() == 1) {
			return Result.e(ResultCode.FILE_NOT_EXIST);
		}
		/* 设置文件夹信息 如果有 */
		if (folderTimestamp != null) {
			File folderInfo = new File();
			folderInfo.setTimestamp(folderTimestamp);
			folderInfo.setName(folderName);
			file.setFolderInfo(folderInfo);
		}
		return Result.ok(file);
	}

	/**
	 * 文件夹里的文件信息
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/folder/file/info/{timestamp}")
	public Result fileFolderInfo(HttpSession session, @PathVariable String timestamp) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		User user = new User();
		/* 查询用户信息 */
		User u = userServer.findById(id);
		user.setSizeone(u.getSizeone());
		/* 查询文件信息 */
		File f = fileServer.findByTimestamp(timestamp);
		if (f == null) {
			return Result.e(ResultCode.FILE_NOT_EXIST);
		}
		if (!f.getUserid().equals(id)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		List<Folder> folders = folderServer.findByFolderId(f.getId());
		List<File> files = new ArrayList<File>();
		for (Folder folder : folders) {
			File file = fileServer.findById(folder.getFileid());
			if (file.getIsdelete() == 0) {
				files.add(file);
			}
		}
		ListUtils.sort(files, true, "name");// 按照文件名排序
		user.setFolderInfo(f);
		user.setFiles(files);
		return Result.ok(user);
	}

	/**
	 * 编辑文件
	 * 
	 * @param session
	 * @param file
	 * @return
	 */
	@PostMapping("/file/edit")
	public Result fileEdit(HttpSession session, @RequestBody FileVo file) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 判断是否为当前用户的文件 */
		File ff = fileServer.findById(file.getId());
		if (!ff.getUserid().equals(id)) {
			return Result.e(ResultCode.INVALID_OPERATION);// 非法操作
		}
		/* 修改文件信息 */
		FileVo f = new FileVo();
		f.setId(file.getId());
		f.setName(file.getName());
		f.setNameindex(StringUtils.getAnsj(file.getName()));
		f.setDescription(file.getDescription());
		f.setIsdelete(file.getIsdelete());
		f.setIsshare(file.getIsshare());
		f.setIssearch(file.getIssearch());
		f.setIslimit(file.getIslimit());
		f.setPwd(file.getPwd());
		Integer ok = fileServer.update(f);
		if (ok == 0) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
		return Result.ok();
	}

	/**
	 * 上传文件
	 * 
	 * @param session
	 * @param file
	 * @param folderid
	 * @return
	 */
	@PostMapping(value = { "/file/upload", "/file/upload/{folderid}" })
	public Result fileUpload(HttpSession session, MultipartFile file,
			@PathVariable(required = false) Integer folderid) {
		try {
			/* 判断用户是否登录 */
			Integer id = (Integer) session.getAttribute("id");
			if (id == null) {
				return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
			}
			/* 判断文件是否为空 */
			if (file == null || file.isEmpty()) {
				return Result.e(ResultCode.FILE_IS_BLANK);// 文件为空
			}
			User u = userServer.findById(id);
			if (u.getSizecurrent() > u.getSizeall()) {
				return Result.e(ResultCode.CAPACITY_IS_INSUFFICIENT);// 空间不足
			}
			if (file.getSize() > (u.getSizeone() << 20)) {// 转为B
				return Result.e(ResultCode.FILE_IS_TOO_LARGE);// 文件过大
			}
			/* 增加当前使用的存储空间 */
			UserVo uv = new UserVo();
			uv.setId(id);
			uv.setSizecurrent(u.getSizecurrent() + (((int) file.getSize() - 1) >> 20) + 1);// 转化为MB
			userServer.update(uv);
			/* 导入文件各种信息 */
			String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
			FileVo file1 = new FileVo();
			String timestamp = ConversionUtils._10_to_62(DateUtils.getSyncTimestamp());
			file1.setUserid(id);
			file1.setName(file.getOriginalFilename());
			file1.setNameindex(StringUtils.getAnsj(file.getOriginalFilename()));
			file1.setLength(file.getSize());
			file1.setTimestamp(timestamp);
			file1.setMd5(md5);
			file1.setUuid(StringUtils.getUuid());
			file1.setIsfolder(0);
			file1.setTime(DateUtils.getIntTimestamp());
			if (folderid == null) {// 普通文件
				file1.setIssubfile(0);
			} else {// 子文件
				file1.setIssubfile(1);
			}
			/* 上传文件 */
			File md5ok = fileServer.findByMD5(md5);
			if (md5ok == null) {
				FileUpDownLoadUtils.uploadReAll(Constant.FILE_SAVE_PATH, timestamp, file);
				file1.setPath(timestamp);
			} else {
				file1.setPath(md5ok.getTimestamp());
			}
			fileServer.insert(file1);
			/* 上传到文件夹 */
			if (folderid != null) {
				FolderVo folder = new FolderVo();
				folder.setFolderid(folderid);
				folder.setFileid(file1.getId());
				folderServer.insert(folder);
			}
			return Result.ok(file1);
		} catch (Exception e) {
			return Result.e(ResultCode.SYSTEM_INNER_ERROR);// 系统繁忙
		}
	}

	/**
	 * 批量删除文件
	 * 
	 * @param session
	 * @return
	 */
	@PostMapping("/file/delete")
	public Result fileDelete(HttpSession session, @RequestBody List<String> uuids) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		Integer countSize = 0;
		List<Integer> ok = new ArrayList<Integer>();
		for (String uuid : uuids) {
			/* 判断是否为当前用户的文件 */
			File ff = fileServer.findByUuid(uuid);
			if (ff.getUserid().equals(id)) {
				/* 修改文件信息 */
				FileVo f = new FileVo();
				f.setId(ff.getId());
				f.setIsdelete(1);
				fileServer.update(f);
				ok.add(ff.getId());
				countSize += ((int) (ff.getLength() - 1) >> 20) + 1;
			}
		}
		/* 减少当前使用的存储空间 */
		if (countSize == 0) {
			return Result.ok(ok);
		}
		User u = userServer.findById(id);
		UserVo uv = new UserVo();
		uv.setId(id);
		uv.setSizecurrent(u.getSizecurrent() - countSize);// 转化为MB
		userServer.update(uv);
		return Result.ok(ok);
	}

	/**
	 * 新建文件夹
	 * 
	 * @param session
	 * @param folderName
	 * @return
	 */
	@PostMapping("/file/newFolder/{folderName}")
	public Result fileNewFolder(HttpSession session, @PathVariable String folderName) {
		/* 判断用户是否登录 */
		Integer id = (Integer) session.getAttribute("id");
		if (id == null) {
			return Result.e(ResultCode.USER_NOT_LOGGED_IN);// 未登录
		}
		/* 保存文件夹信息 */
		FileVo file1 = new FileVo();
		String timestamp = ConversionUtils._10_to_62(DateUtils.getSyncTimestamp());
		file1.setUserid(id);
		file1.setName(folderName);
		file1.setNameindex(StringUtils.getAnsj(folderName));
		file1.setLength(0L);
		file1.setTimestamp(timestamp);
		file1.setPath("0");
		file1.setMd5("0");
		file1.setUuid(StringUtils.getUuid());
		file1.setIsfolder(1);
		file1.setIssubfile(0);
		file1.setTime(DateUtils.getIntTimestamp());
		fileServer.insert(file1);
		return Result.ok(file1);
	}
}
