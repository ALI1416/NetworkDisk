package com.ck.constant;

/**
 * 从yml文件读取的常数
 * 
 * @author ALI
 *
 */
//@Configuration
//@ConfigurationProperties(prefix = "constant")
public class Constant1 {
	/**
	 * 上传文件的保存位置
	 */
	private String fileSavePath;
	/**
	 * 临时文件的保存位置
	 */
	private String fileTempPath;
	/**
	 * 教师单个文件最大容量
	 */
	private Integer teacherSizeOne;
	/**
	 * 教师最大容量
	 */
	private Integer teacherSizeAll;
	/**
	 * 学生单个文件最大容量
	 */
	private Integer studentSizeOne;
	/**
	 * 学生最大容量
	 */
	private Integer studentSizeAll;

	public String getFileSavePath() {
		return fileSavePath;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public String getFileTempPath() {
		return fileTempPath;
	}

	public void setFileTempPath(String fileTempPath) {
		this.fileTempPath = fileTempPath;
	}

	public Integer getTeacherSizeOne() {
		return teacherSizeOne;
	}

	public void setTeacherSizeOne(Integer teacherSizeOne) {
		this.teacherSizeOne = teacherSizeOne;
	}

	public Integer getTeacherSizeAll() {
		return teacherSizeAll;
	}

	public void setTeacherSizeAll(Integer teacherSizeAll) {
		this.teacherSizeAll = teacherSizeAll;
	}

	public Integer getStudentSizeOne() {
		return studentSizeOne;
	}

	public void setStudentSizeOne(Integer studentSizeOne) {
		this.studentSizeOne = studentSizeOne;
	}

	public Integer getStudentSizeAll() {
		return studentSizeAll;
	}

	public void setStudentSizeAll(Integer studentSizeAll) {
		this.studentSizeAll = studentSizeAll;
	}

}
