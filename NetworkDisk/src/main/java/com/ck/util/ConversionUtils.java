package com.ck.util;

public class ConversionUtils {

	public static String _10_to_62(long n) {
		if (n <= 0) {
			return "0";
		}
		String s = "";
		long a = 0;
		while (n > 0) {
			a = n % 62;
			if (a < 10) {
				s = (char) (a + 48) + s;// 转为0-9
			} else if (a < 36) {
				s = (char) (a + 55) + s;// 转为A-Z
			} else {
				s = (char) (a + 61) + s;// 转为a-z
			}
			n /= 62;
		}
		return s;
	}

	public static long _62_to_10(String s) {
		long n = 0;
		long p = 1;
		int c = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			c = s.charAt(i);
			if (c > 96) {
				n += (c - 61) * p;// a-z转为数字
			} else if (c > 64) {
				n += (c - 55) * p;// A-Z转为数字
			} else {
				n += (c - 48) * p;// 0-9转为数字
			}
			p *= 62;
		}
		return n;
	}

	public static long ip2Long(String str) {
		String[] s = str.split("\\.");
		return (Long.parseLong(s[0]) << 24) | (Long.parseLong(s[1]) << 16) | (Long.parseLong(s[2]) << 8)
				| (Long.parseLong(s[3]));
	}

	public static String long2Ip(long n) {
		return (n >> 24 & 0xFF) + "." + (n >> 16 & 0xFF) + "." + (n >> 8 & 0xFF) + "." + (n & 0xFF);
	}

	public static void main(String[] args) {
		System.out.println("1570283088299L=" + _10_to_62(1570283088299L));
		System.out.println("Re27oHT=" + _62_to_10("Re27oHT"));
		System.out.println("192.168.0.1=" + ip2Long("192.168.0.1"));
		System.out.println("3232235521=" + long2Ip(3232235521L));
	}
}
