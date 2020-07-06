package com.ck.ctrl.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserCtrlRedirect {

	@GetMapping("/login")
	public String userLogin() {
		return "user/login";
	}

	@GetMapping("/register")
	public String userRegister() {
		return "user/register";
	}

	@GetMapping("/edit")
	public String userEdit() {
		return "user/edit";
	}

	@GetMapping("/find")
	public String userFind() {
		return "user/find";
	}

	@GetMapping("/changePwd")
	public String userChangPwd() {
		return "user/changePwd";
	}

	@GetMapping("/user/file/info")
	public String userFileInfo() {
		return "user/userFileInfo";
	}

	@GetMapping("/file/info/{id}")
	public String fileInfo() {
		return "user/fileInfo";
	}

	@GetMapping("/folder/file/info/{id}")
	public String fileFolderFileInfo() {
		return "user/userFileInfo";
	}
}