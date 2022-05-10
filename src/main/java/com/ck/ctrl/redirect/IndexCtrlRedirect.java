package com.ck.ctrl.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrlRedirect {
	@GetMapping(value = { "", "/", "/index", "/z/{z}", "/f/{f}", "/u/{u}", "/jump" })
	public String index() {
		return "index";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
