package com.ck.ctrl;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ck.result.Result;

@RestController
public class IndexCtrl {

	@PostMapping("/user/logout")
	public Result userLogout(HttpSession session) {
		session.invalidate();
		return Result.ok();
	}

	@PostMapping(value = { "", "/", "index" })
	public Result index(HttpSession session) {
		String account = (String) session.getAttribute("account");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		return Result.ok(map);
	}

	@PostMapping("/z/{z}")
	public Result z(HttpSession session, @PathVariable String z) {
		String account = (String) session.getAttribute("account");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("link", "/file/file/info/" + z);
		return Result.ok(map);
	}

	@PostMapping("/f/{f}")
	public Result f(HttpSession session, @PathVariable String f) {
		String account = (String) session.getAttribute("account");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("link", "/file/folder/file/info/" + f);
		return Result.ok(map);
	}

	@PostMapping("/u/{u}")
	public Result u(HttpSession session, @PathVariable String u) {
		String account = (String) session.getAttribute("account");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("link", "/file/user/info/" + u);
		return Result.ok(map);
	}

	@PostMapping("/jump")
	public Result jump(HttpSession session, String jump) {
		String account = (String) session.getAttribute("account");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", account);
		map.put("jump", jump);
		return Result.ok(map);
	}
}
