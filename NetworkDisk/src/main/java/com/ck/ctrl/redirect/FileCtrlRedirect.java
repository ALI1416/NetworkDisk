package com.ck.ctrl.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("file")
public class FileCtrlRedirect {

	@GetMapping("/user/info/{id}")
	public String userInfo() {
		return "file/userInfo";
	}

	@GetMapping("/user/file/info/{id}")
	public String userFileInfo() {
		return "file/userFileInfo";
	}

	@GetMapping("/file/info/{id}")
	public String fileInfo() {
		return "file/fileInfo";
	}

	@GetMapping("/folder/info/{id}")
	public String fileFolderInfo() {
		return "file/folderInfo";
	}

	@GetMapping("/folder/file/info/{id}")
	public String fileFolderFileInfo() {
		return "file/userFileInfo";
	}

	@GetMapping("/search")
	public String fileSearch() {
		return "file/search";
	}

	@GetMapping("/z/{file}")
	public String z() {
		return "file/search";
	}
}
