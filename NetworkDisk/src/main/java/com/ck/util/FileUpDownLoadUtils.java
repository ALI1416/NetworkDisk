package com.ck.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载工具类
 * 
 * @author ALI
 *
 */
public class FileUpDownLoadUtils {

	public static boolean upload(String path, MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				file.transferTo(new File(path + file.getOriginalFilename()));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean uploadReName(String path, String name, MultipartFile file) {
		if (!file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			String newFilename = "";
			if (name == null || "".equals(name)) {
				newFilename = originalFilename;
			} else {
				newFilename = name + originalFilename.substring(originalFilename.lastIndexOf('.'));
			}
			try {
				file.transferTo(new File(path + newFilename));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean uploadReAll(String path, String all, MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				file.transferTo(new File(path + all));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static ResponseEntity<byte[]> download(HttpServletRequest request, String userAgent, String path,
			String newFileName) {
		try {
			File file = new File(path);
			BodyBuilder builder = ResponseEntity.ok();
			builder.contentLength(file.length());
			builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
			if (newFileName == null) {
				newFileName = path.substring(path.lastIndexOf("/") + 1, path.length());
			}
			newFileName = URLEncoder.encode(newFileName, "UTF-8");
			if (userAgent.indexOf("MSIE") > 0) {
				builder.header("Content-Disposition", "attachment;filename=" + newFileName);
			} else {
				builder.header("Content-Disposition", "attachment;filename*=UTF-8''" + newFileName);
			}
			return builder.body(FileUtils.readFileToByteArray(file));
		} catch (Exception e) {
			return null;
		}
	}

	public static ResponseEntity<byte[]> jpeg(String path) {
		try {
			File file = new File(path);
			BodyBuilder builder = ResponseEntity.ok();
			builder.contentType(MediaType.IMAGE_JPEG);
			return builder.body(FileUtils.readFileToByteArray(file));
		} catch (Exception e) {
			return null;
		}
	}

	public static ResponseEntity<byte[]> text(String path) {
		try {
			BodyBuilder builder = ResponseEntity.ok();
			builder.contentType(MediaType.TEXT_PLAIN);
			return builder.body(FileUtils.readFileToByteArray(new File(path)));
		} catch (Exception e) {
			return null;
		}
	}

	public static ResponseEntity<byte[]> text(String path, int size) {
		try {
			BodyBuilder builder = ResponseEntity.ok();
			builder.contentType(MediaType.TEXT_PLAIN);
			FileInputStream stream = new FileInputStream(path);
			int fileSize = stream.available();
			size = fileSize < size ? fileSize : size;
			byte[] b = new byte[size];
			stream.read(b);
			stream.close();
			return builder.body(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static ResponseEntity<byte[]> str(String str) {
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.TEXT_PLAIN);
		return builder.body(str.getBytes());
	}

	public static ResponseEntity<byte[]> large() {
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.TEXT_PLAIN);
		return builder.body("file is too large".getBytes());
	}

	public static ResponseEntity<byte[]> empty() {
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.TEXT_PLAIN);
		return builder.body("file is empty".getBytes());
	}

	public static ResponseEntity<byte[]> notSupport() {
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.TEXT_PLAIN);
		return builder.body("file not support".getBytes());
	}

	public static ResponseEntity<byte[]> notFound() {
		BodyBuilder builder = ResponseEntity.ok();
		builder.contentType(MediaType.TEXT_PLAIN);
		return builder.body("file not found".getBytes());
	}

	public static ResponseEntity<byte[]> downloadZip(HttpServletRequest request, String userAgent,
			List<String> filePath, List<String> newFileName, String zipPath) {
		try {
			File file = new File(zipPath);
			file.createNewFile();
			FileInputStream fileInputStream = null;
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = null;
			for (int i = 0; i < filePath.size(); i++) {
				File file1 = new File(filePath.get(i));
				fileInputStream = new FileInputStream(file1);
				zipEntry = new ZipEntry(newFileName.get(i));
				zipOutputStream.putNextEntry(zipEntry);
				int len = 0;
				byte[] buffer = new byte[4096];
				while ((len = fileInputStream.read(buffer)) > 0) {
					zipOutputStream.write(buffer, 0, len);
				}
			}
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileInputStream.close();
			fileOutputStream.close();
			String fileName = zipPath.substring(zipPath.lastIndexOf("/") + 1, zipPath.length());
			return download(request, userAgent, fileName, zipPath);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] s) {
		List<String> filePath = new ArrayList<String>();
		List<String> newFileName = new ArrayList<String>();
		filePath.add("E:/Pictures/goods/1.png");
		filePath.add("E:/Pictures/goods/2.png");
		newFileName.add("壹.txt");
		newFileName.add("贰.txt");
		String zipPath = "E:/zip.zip";
		String fileName = zipPath.substring(zipPath.lastIndexOf("/") + 1, zipPath.length());
		System.out.println(fileName);
	}
}
