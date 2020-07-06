package com.ck.po;

import java.util.List;

/**
 * 文件持久层
 * 
 * @author ALI
 *
 */
public class File {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 外键。用户id
	 */
	private Integer userid;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件名全文索引
	 */
	private String nameindex;
	/**
	 * 文件描述
	 */
	private String description;
	/**
	 * 文件密码
	 */
	private String pwd;
	/**
	 * 文件长度。单位B
	 */
	private Long length;
	/**
	 * 文件上传时间戳
	 */
	private String timestamp;
	/**
	 * 文件保存的路径
	 */
	private String path;
	/**
	 * 文件MD5
	 */
	private String md5;
	/**
	 * 文件下载地址
	 */
	private String uuid;
	/**
	 * 文件浏览次数
	 */
	private Integer browsing;
	/**
	 * 文件下载次数
	 */
	private Integer download;
	/**
	 * 0未删除1已删除
	 */
	private Integer isdelete;
	/**
	 * 0私有1共享
	 */
	private Integer isshare;
	/**
	 * 0不可搜索1可搜索
	 */
	private Integer issearch;
	/**
	 * 0不限制下载1限制下载
	 */
	private Integer islimit;
	/**
	 * 0文件1文件夹
	 */
	private Integer isfolder;
	/**
	 * 0文件1子文件
	 */
	private Integer issubfile;
	/**
	 * 文件上传时间
	 */
	private Integer time;
	/**
	 * 一对一。用户
	 */
	private User user;
	/**
	 * 一对多。文件夹
	 */
	private List<Folder> folder;
	/**
	 * 一对多。浏览历史
	 */
	private List<HistoryBrowsing> historyBrowsing;
	/**
	 * 一对多。下载历史
	 */
	private List<HistoryDownload> historyDownload;
	/**
	 * 文件夹信息
	 */
	private File folderInfo;
	/**
	 * 文件列表
	 */
	private List<File> files;

	public File getFolderInfo() {
		return folderInfo;
	}

	public void setFolderInfo(File folderInfo) {
		this.folderInfo = folderInfo;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameindex() {
		return nameindex;
	}

	public void setNameindex(String nameindex) {
		this.nameindex = nameindex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getBrowsing() {
		return browsing;
	}

	public void setBrowsing(Integer browsing) {
		this.browsing = browsing;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getIsshare() {
		return isshare;
	}

	public void setIsshare(Integer isshare) {
		this.isshare = isshare;
	}

	public Integer getIssearch() {
		return issearch;
	}

	public void setIssearch(Integer issearch) {
		this.issearch = issearch;
	}

	public Integer getIslimit() {
		return islimit;
	}

	public void setIslimit(Integer islimit) {
		this.islimit = islimit;
	}

	public Integer getIsfolder() {
		return isfolder;
	}

	public void setIsfolder(Integer isfolder) {
		this.isfolder = isfolder;
	}

	public Integer getIssubfile() {
		return issubfile;
	}

	public void setIssubfile(Integer issubfile) {
		this.issubfile = issubfile;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Folder> getFolder() {
		return folder;
	}

	public void setFolder(List<Folder> folder) {
		this.folder = folder;
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

	@Override
	public String toString() {
		return "File [id=" + id + ", userid=" + userid + ", name=" + name + ", nameindex=" + nameindex
				+ ", description=" + description + ", pwd=" + pwd + ", length=" + length + ", timestamp=" + timestamp
				+ ", path=" + path + ", md5=" + md5 + ", uuid=" + uuid + ", browsing=" + browsing + ", download="
				+ download + ", isdelete=" + isdelete + ", isshare=" + isshare + ", issearch=" + issearch + ", islimit="
				+ islimit + ", isfolder=" + isfolder + ", issubfile=" + issubfile + ", time=" + time + ", user=" + user
				+ ", folder=" + folder + ", historyBrowsing=" + historyBrowsing + ", historyDownload=" + historyDownload
				+ ", folderInfo=" + folderInfo + ", files=" + files + "]";
	}

}
