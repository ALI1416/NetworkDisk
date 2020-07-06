package com.ck.vo;

/**
 * 文件值对象
 * 
 * @author ALI
 *
 */
public class FileVo {
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
	 * 文件密码否定
	 */
	private String pwdNot;
	/**
	 * 文件长度。单位B
	 */
	private Long length;
	/**
	 * 长度结束，不是数据库字段
	 */
	private Long lengthEnd;
	/**
	 * 长度否定，不是数据库字段
	 */
	private Long lengthNot;
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
	 * 浏览次数结束，不是数据库字段
	 */
	private Integer browsingEnd;
	/**
	 * 浏览次数否定，不是数据库字段
	 */
	private Integer browsingNot;
	/**
	 * 文件下载次数
	 */
	private Integer download;
	/**
	 * 下载次数结束，不是数据库字段
	 */
	private Integer downloadEnd;
	/**
	 * 下载次数否定，不是数据库字段
	 */
	private Integer downloadNot;
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
	 * 0文件1文件夹2子文件
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
	 * 时间结束，不是数据库字段
	 */
	private Integer timeEnd;
	/**
	 * 时间否定，不是数据库字段
	 */
	private Integer timeNot;
	/**
	 * 排序
	 */
	private String orderBy;

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

	public Long getLengthEnd() {
		return lengthEnd;
	}

	public void setLengthEnd(Long lengthEnd) {
		this.lengthEnd = lengthEnd;
	}

	public Long getLengthNot() {
		return lengthNot;
	}

	public void setLengthNot(Long lengthNot) {
		this.lengthNot = lengthNot;
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

	public Integer getBrowsingEnd() {
		return browsingEnd;
	}

	public void setBrowsingEnd(Integer browsingEnd) {
		this.browsingEnd = browsingEnd;
	}

	public Integer getBrowsingNot() {
		return browsingNot;
	}

	public void setBrowsingNot(Integer browsingNot) {
		this.browsingNot = browsingNot;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getDownloadEnd() {
		return downloadEnd;
	}

	public void setDownloadEnd(Integer downloadEnd) {
		this.downloadEnd = downloadEnd;
	}

	public Integer getDownloadNot() {
		return downloadNot;
	}

	public void setDownloadNot(Integer downloadNot) {
		this.downloadNot = downloadNot;
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

	public Integer getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Integer timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getTimeNot() {
		return timeNot;
	}

	public void setTimeNot(Integer timeNot) {
		this.timeNot = timeNot;
	}

	public String getPwdNot() {
		return pwdNot;
	}

	public void setPwdNot(String pwdNot) {
		this.pwdNot = pwdNot;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "FileVo [id=" + id + ", userid=" + userid + ", name=" + name + ", nameindex=" + nameindex
				+ ", description=" + description + ", pwd=" + pwd + ", pwdNot=" + pwdNot + ", length=" + length
				+ ", lengthEnd=" + lengthEnd + ", lengthNot=" + lengthNot + ", timestamp=" + timestamp + ", path="
				+ path + ", md5=" + md5 + ", uuid=" + uuid + ", browsing=" + browsing + ", browsingEnd=" + browsingEnd
				+ ", browsingNot=" + browsingNot + ", download=" + download + ", downloadEnd=" + downloadEnd
				+ ", downloadNot=" + downloadNot + ", isdelete=" + isdelete + ", isshare=" + isshare + ", issearch="
				+ issearch + ", islimit=" + islimit + ", isfolder=" + isfolder + ", issubfile=" + issubfile + ", time="
				+ time + ", timeEnd=" + timeEnd + ", timeNot=" + timeNot + ", orderBy=" + orderBy + "]";
	}

}
