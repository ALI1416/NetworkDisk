package com.ck.po;

/**
 * 举报持久层
 * 
 * @author ALI
 *
 */
public class TipOff {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 外键。用户id
	 */
	private Integer userid;
	/**
	 * 外键。文件id
	 */
	private Integer fileid;
	/**
	 * 举报内容
	 */
	private String text;
	/**
	 * 举报时间
	 */
	private Integer time;
	/**
	 * 举报者IP
	 */
	private Long ip;
	/**
	 * 举报状态
	 */
	private Integer status;

	public Integer getFileid() {
		return fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "TipOff [id=" + id + ", userid=" + userid + ", fileid=" + fileid + ", text=" + text + ", time=" + time
				+ ", ip=" + ip + ", status=" + status + "]";
	}

}
