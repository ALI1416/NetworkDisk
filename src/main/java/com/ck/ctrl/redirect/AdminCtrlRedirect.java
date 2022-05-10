package com.ck.ctrl.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminCtrlRedirect {

	@GetMapping("/file")
	public String fileAll() {
		return "admin/fileAll";
	}

	@GetMapping("/folder/{id}")
	public String fileFolder() {
		return "admin/folder";
	}

	@GetMapping("/user")
	public String userAll() {
		return "admin/userAll";
	}

	@GetMapping("/realname")
	public String realnameAll() {
		return "admin/realnameAll";
	}

	@GetMapping("/historyDownload")
	public String historyDownloadAll() {
		return "admin/historyDownloadAll";
	}

	@GetMapping("/historyBrowsing")
	public String historyBrowsingAll() {
		return "admin/historyBrowsingAll";
	}

	@GetMapping("/tipOff")
	public String tipOffAll() {
		return "admin/tipOffAll";
	}

	@GetMapping("/info/{type}/{id}")
	public String info() {
		return "admin/info";
	}

}