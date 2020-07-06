package com.ck.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class IdCardUtils {
	/**
	 * 身份证校验 身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
	 *
	 * @param idcard
	 * @return
	 */
	public static boolean regIdCard(String idcard) {
		try {
			if (idcard.length() != 18) {
				return false;
			}
			String reg = "^[0-9]{17}[0-9xX]$";
			if (!Pattern.matches(reg, idcard)) {
				return false;
			}
			idcard = idcard.toLowerCase();
			/* 取身份证前两位,校验省份 */
			int city[] = { 11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51,
					52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82 };
			if (Arrays.binarySearch(city, Integer.parseInt(idcard.substring(0, 2))) < 0) {
				return false;
			}
			/* 检查出生年月日是否正确 */
			int year = Integer.parseInt(idcard.substring(6, 10));
			int month = Integer.parseInt(idcard.substring(11, 12));
			int day = Integer.parseInt(idcard.substring(13, 14));
			// 年月日是否合理
			if (month < 1 || month > 12) {
				return false;
			}
			if (day < 1 || day > 31) {
				return false;
			}
			if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) {
				return false;
			}
			if (month == 2) {
				boolean leap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
				if (day > 29 || (day == 29 && !leap)) {
					return false;
				}
			}
			// 判断年份的范围（0岁到200岁之间)
			Calendar cal = Calendar.getInstance();// 使用日历类
			int now_year = cal.get(Calendar.YEAR);// 得到年
			int time = now_year - year;
			if (time < 0 || time > 200) {
				return false;
			}
			/* 校验位的检测 */
			int arrInt[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			String arrCh[] = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
			int cardTemp = 0;
			for (int i = 0; i < 17; i++) {
				cardTemp += Integer.parseInt(idcard.substring(i, i + 1)) * arrInt[i];
			}
			if (!arrCh[cardTemp % 11].equals(idcard.substring(17, 18))) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String s[]) {
		System.out.println(regIdCard("370811199805255017"));
		System.out.println(regIdCard("11010119900307301X"));
	}
}
