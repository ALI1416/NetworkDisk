package com.ck.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author ALI
 *
 */
public class DateUtils {
	/**
	 * 获取int型时间戳
	 * 
	 * @return int型时间戳
	 */
	public static int getIntTimestamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 同步时间戳1ms生成1个
	 * 
	 * @return 时间戳
	 */
	public synchronized static long getSyncTimestamp() {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
		}
		return System.currentTimeMillis();
	}

	/**
	 * 获取日期 yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @return 日期 yyyy-MM-dd HH:mm:ss格式
	 */
	public static String getDatetime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 获取日期，自定义格式
	 * 
	 * @param format 日期参数 G 纪元标记 yyyy 年份 MM 月份 dd 日 hh 12小时制 HH 24小时制 mm 分钟 ss 秒 SS
	 *               毫秒 E 星期 D 一年中第几天 F 一个月中第几天的周几 w 一年中的第几周 W 一个月中的第几周 a AM/PM标记 k
	 *               一天中的第几小时(1-24) K AM/PM格式一天中的第几小时(0-11) z 时区 ' 文本定界符 " 单引号
	 * 
	 * @return 日期
	 */
	public static String getDatetime(String format) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 时间戳转日期yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param timestamp 时间戳
	 * @return 日期yyyy-MM-dd HH:mm:ss格式
	 */
	public static String timestamp2Date(long timestamp) {
		SimpleDateFormat sdf = null;
		Date d = new Date(timestamp);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	public static void main(String[] args) {
		Thread test1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + " : " + DateUtils.getSyncTimestamp());
				}
			}
		}, "111");
		Thread test2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + " : " + DateUtils.getSyncTimestamp());
				}
			}
		}, "222");
		test1.start();
		test2.start();
	}
}
