package com.ck;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.util.DigestUtils;

import com.ck.po.User;
import com.ck.result.Result;
import com.ck.result.ResultCode;
import com.ck.util.BCryptUtils;
import com.ck.util.ConversionUtils;
import com.ck.vo.UserVo;

public class Test {

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		long timestamp = System.currentTimeMillis();
		System.out.println("uuid=" + uuid);
		System.out.println("timestamp=" + timestamp);
		SimpleDateFormat sf = null;
		Date d = new Date(timestamp);
		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		System.out.println("date=" + sf.format(d));
		String path = "D:/file/1.png";
		String md5 = "";
		String filename = "";
		long length = 0;
		try {
			md5 = DigestUtils.md5DigestAsHex(new FileInputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("md5=" + md5);
		File file = new File(path);
		length = file.length();
		filename = file.getName();
		System.out.println("length=" + length);
		System.out.println("filename=" + filename);
		String _62 = ConversionUtils._10_to_62(timestamp);
		long _10 = ConversionUtils._62_to_10(_62);
		System.out.println("_62=" + _62);
		System.out.println("_10=" + _10);
		System.out.println(Result.ok());
		System.out.println(Result.ok("12121"));
		System.out.println(Result.e(ResultCode.CAPACITY_IS_INSUFFICIENT));
		System.out.println(Result.e(ResultCode.SYSTEM_INNER_ERROR, "rewrere"));
		String pwd = "";
		try {
			pwd = BCryptUtils.encode(DigestUtils.md5DigestAsHex("chengkai".getBytes()));
//			pwd = BCryptUtils.encode("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(pwd);
//		String s = "随着5G的不断发展，人们对于网络硬盘的需求愈发上升，但现有的网盘对用户限制太多，对用户非常不友好，如限制下载速度、限制储存容量、文件容易丢失、广告众多、捆绑软件、泄露用户隐私等问题。所以我们需要一个更清洁、更安全、更稳定、下载速度更快的网盘系统。";
//		System.out.println(StringUtils.getAnsj(s));
		Object a = null;
		Integer id = (Integer) a;
		System.out.println("id=" + id);
		User u = new User();
		u.setName("12");
		System.out.println(u);
		UserVo uv = new UserVo();
		uv.setUser(u);
		System.out.println(uv);
		Integer a1 = 10;
		Integer a2 = 10;
		Integer a3 = 1000;
		Integer a4 = 1000;
		System.out.println(a1 == a2);
		System.out.println(a3 == a4);
		System.out.println(a1 == 10);
		System.out.println(a3 == 1000);
		System.out.println(a1.equals(a2));
		System.out.println(a3.equals(a4));
		String content = "ali";
		String pattern = "^[a-zA-Z][0-9a-zA-Z]{2,15}$";
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println("isMatch:" + isMatch);
	}

}
