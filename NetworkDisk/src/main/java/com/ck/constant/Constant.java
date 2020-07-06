package com.ck.constant;

public class Constant {
	/**
	 * 上传文件的保存位置D:/file/
	 */
	public static final String FILE_SAVE_PATH = "D:/file/";
	/**
	 * 临时文件的保存位置D:/temp/
	 */
	public static final String FILE_TEMP_PATH = "D:/temp/";
	/**
	 * 教师单个文件最大容量2GB
	 */
	public static final Integer TEACHER_SIZE_ONE = 1 << 11 - 1;// 2GB，转换成直接时2048会溢出，所以要-1
	/**
	 * 教师最大容量1TB
	 */
	public static final Integer TEACHER_SIZE_ALL = 1 << 20;// 1TB
	/**
	 * 学生单个文件最大容量256MB
	 */
	public static final Integer STUDENT_SIZE_ONE = 1 << 8;// 256MB
	/**
	 * 学生最大容量16GB
	 */
	public static final Integer STUDENT_SIZE_ALL = 1 << 14;// 16GB
	/**
	 * 文本预览最大容量32KB
	 */
	public static final Integer TEXT_PREVIEW_MAX = 1 << 14;// 32KB
	/**
	 * 图片压缩最大宽1920像素
	 */
	public static final Integer PICTURE_COMPRESS_WIDTH_MAX = 1920;// 1920像素
	/**
	 * 图片压缩最大高1920像素
	 */
	public static final Integer PICTURE_COMPRESS_HEIGHT_MAX = 1920;// 1920像素
	/**
	 * 图片压缩最小容量64KB
	 */
	public static final Integer PICTURE_COMPRESS_MIN = 1 << 16;// 64KB
	/**
	 * 图片压缩最大容量64MB
	 */
	public static final Integer PICTURE_COMPRESS_MAX = 1 << 26;// 64MB
	/**
	 * 图片压缩最小容量差64KB
	 */
	public static final Integer PICTURE_COMPRESS_DIFFER_MIN = 1 << 16;// 64KB
	/**
	 * 打包下载最大容量256MB
	 */
	public static final Integer PACKAGE_DOWMLOAD_MAX = 1 << 28;// 256MB

}
