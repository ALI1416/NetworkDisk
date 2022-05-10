package com.ck.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	public static void main(String[] args) {
		List<String> filePath = new ArrayList<String>();
		List<String> newFileName = new ArrayList<String>();
		filePath.add("E:/Pictures/goods/1.png");
		filePath.add("E:/Pictures/goods/2.png");
		newFileName.add("1.png");
		newFileName.add("1.png");
		String zipPath = "E:/压缩文件.zip";
		zipFiles(filePath, newFileName, zipPath);
	}

	public static void zipFiles(List<String> filePath, List<String> newFileName, String zipPath) {
		try {
			File zipFile = new File(zipPath);
			if (!zipFile.exists()) {
				zipFile.createNewFile();
			}
			newFileName = StringUtils.duplicateRenameFile(newFileName);
			FileInputStream fileInputStream = null;
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = null;
			for (int i = 0; i < filePath.size(); i++) {
				File file = new File(filePath.get(i));
				fileInputStream = new FileInputStream(file);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}