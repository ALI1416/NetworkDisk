package com.ck.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.ansj.splitWord.analysis.ToAnalysis;

/**
 * 
 * @author ALI
 *
 */
public class StringUtils {

	/**
	 * ansj分词
	 * 
	 * @param s
	 * @return
	 */
	public static String getAnsj(String s) {
		return ToAnalysis.parse(s).toStringWithOutNature(" ").replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "")
				.replaceAll(" +", " ");
	}

	/**
	 * 64位UUID
	 * 
	 * @return
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符串打码
	 * 
	 * @param str
	 * @return
	 */
	public static String getMask(String str) {
		String mask = "***";
		StringBuffer sb = new StringBuffer(str);
		if (sb.length() > 2) {
			return str = sb.replace(1, sb.length() - 1, mask).toString();
		}
		return mask;
	}

	/**
	 * 随机4个字符a-z,0-9
	 * 
	 * @return
	 */
	public static String getRandom4() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		String s = "";
		for (int i = 0; i < 4; i++) {
			s += base.charAt(random.nextInt(36));
		}
		return s;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		int a = fileName.lastIndexOf(".") + 1;
		return a == 0 ? "" : fileName.substring(a);
	}

	public static List<String> duplicateRenameStr(List<String> str) {
		return duplicateRename(str, false);
	}

	public static List<String> duplicateRenameFile(List<String> fileName) {
		return duplicateRename(fileName, true);
	}

	public static List<String> duplicateRename(List<String> str, boolean isFile) {
		List<String> strTemp = new ArrayList<String>();
		List<String> strResult = new ArrayList<String>();
		List<Integer> strCount = new ArrayList<Integer>();
		for (String s : str) {
			if (!strResult.contains(s)) {
				strTemp.add(s);
				strResult.add(s);
				strCount.add(1);
			} else {
				int index = strTemp.indexOf(s);
				Integer count = strCount.get(index);
				String countStr = "";
				if (isFile) {
					int dot = s.lastIndexOf(".");
					if (dot == -1) {
						countStr = s + "(" + count + ")";
					} else {
						countStr = s.substring(0, dot) + "(" + count + ")" + s.substring(dot);
					}
				} else {
					countStr = s + "(" + count + ")";
				}
				strCount.set(index, count + 1);
				strResult.add(countStr);
			}
		}
		if (str.size() == strTemp.size()) {// 没有重复的
			return str;
		}
		return strResult;
	}

	public static void main(String[] args) {
		List<String> str = new ArrayList<String>();
		str.add("a");
		str.add("a");
		str.add("c");
		str.add("a.txt");
		str.add("c.txt");
		str.add("a.txt");
		str.add("c.txt");
		str.add("c.txt");
		str.add("c.txt");
		System.out.println(duplicateRename(str, true));
	}

}
