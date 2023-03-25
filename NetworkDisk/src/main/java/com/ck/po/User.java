package com.ck.po;

import java.util.List;

/**
 * 用户持久层
 * 
 * @author ALI
 *
 */
public class User {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户信息id
	 */
	private Integer userinfoid;
	/**
	 * 用户名
	 */
	private String account;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 性别。1男0女
	 */
	private Integer gender;
	/**
	 * 出生年。默认2000
	 */
	private Integer year;
	/**
	 * 描述。
	 */
	private String description;
	/**
	 * 类型。0已删除1未注册2管理员3教师4学生
	 */
	private Integer type;
	/**
	 * 单文件容量。单位MB
	 */
	private Integer sizeone;
	/**
	 * 总容量。单位MB
	 */
	private Integer sizeall;
	/**
	 * 已使用容量。单位MB
	 */
	private Integer sizecurrent;
	/**
	 * 注册时间
	 */
	private Integer time;
	/**
	 * 文件
	 */
	private File file;
	/**
	 * 文件夹信息
	 */
	private File folderInfo;
	/**
	 * 一对一。用户信息
	 */
	private UserInfo userInfo;
	/**
	 * 一对多。文件
	 */
	private List<File> files;
	/**
	 * 一对多。浏览历史
	 */
	private List<HistoryBrowsing> historyBrowsing;
	/**
	 * 一对多。下载历史
	 */
	private List<HistoryDownload> historyDownload;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserinfoid() {
		return userinfoid;
	}

	public void setUserinfoid(Integer userinfoid) {
		this.userinfoid = userinfoid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSizeone() {
		return sizeone;
	}

	public void setSizeone(Integer sizeone) {
		this.sizeone = sizeone;
	}

	public Integer getSizeall() {
		return sizeall;
	}

	public void setSizeall(Integer sizeall) {
		this.sizeall = sizeall;
	}

	public Integer getSizecurrent() {
		return sizecurrent;
	}

	public void setSizecurrent(Integer sizecurrent) {
		this.sizecurrent = sizecurrent;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<HistoryBrowsing> getHistoryBrowsing() {
		return historyBrowsing;
	}

	public void setHistoryBrowsing(List<HistoryBrowsing> historyBrowsing) {
		this.historyBrowsing = historyBrowsing;
	}

	public List<HistoryDownload> getHistoryDownload() {
		return historyDownload;
	}

	public void setHistoryDownload(List<HistoryDownload> historyDownload) {
		this.historyDownload = historyDownload;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public File getFolderInfo() {
		return folderInfo;
	}

	public void setFolderInfo(File folderInfo) {
		this.folderInfo = folderInfo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userinfoid=" + userinfoid + ", account=" + account + ", pwd=" + pwd + ", name="
				+ name + ", gender=" + gender + ", year=" + year + ", description=" + description + ", type=" + type
				+ ", sizeone=" + sizeone + ", sizeall=" + sizeall + ", sizecurrent=" + sizecurrent + ", time=" + time
				+ ", file=" + file + ", folderInfo=" + folderInfo + ", userInfo=" + userInfo + ", files=" + files
				+ ", historyBrowsing=" + historyBrowsing + ", historyDownload=" + historyDownload + "]";
	}

}
