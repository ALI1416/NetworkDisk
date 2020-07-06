package com.ck.po;

/**
 * 下载历史记录持久层
 * 
 * @author ALI
 *
 */
public class HistoryDownload {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userid;
	/**
	 * 文件id
	 */
	private Integer fileid;
	/**
	 * IP地址
	 */
	private Long ip;
	/**
	 * 下载时间
	 */
	private Integer time;
	/**
	 * 一对一。文件
	 */
	private File file;
	/**
	 * 一对一。用户
	 */
	private User user;

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

	public Integer getFileid() {
		return fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "HistoryDownload [id=" + id + ", userid=" + userid + ", fileid=" + fileid + ", ip=" + ip + ", time="
				+ time + ", file=" + file + ", user=" + user + "]";
	}

}
