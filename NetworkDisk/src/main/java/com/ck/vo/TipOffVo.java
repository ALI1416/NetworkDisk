package com.ck.vo;

/**
 * 举报持久层
 * 
 * @author ALI
 *
 */
public class TipOffVo {
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
	 * 举报状态
	 */
	private Integer status;
	/**
	 * IP地址
	 */
	private Long ip;
	/**
	 * ip结束，不是数据库字段
	 */
	private Long ipEnd;
	/**
	 * ip否定，不是数据库字段
	 */
	private Long ipNot;
	/**
	 * 浏览时间
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

	public Integer getFileid() {
		return fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
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

	public Long getIp() {
		return ip;
	}

	public void setIp(Long ip) {
		this.ip = ip;
	}

	public Long getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(Long ipEnd) {
		this.ipEnd = ipEnd;
	}

	public Long getIpNot() {
		return ipNot;
	}

	public void setIpNot(Long ipNot) {
		this.ipNot = ipNot;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "TipOffVo [id=" + id + ", userid=" + userid + ", fileid=" + fileid + ", text=" + text + ", status="
				+ status + ", ip=" + ip + ", ipEnd=" + ipEnd + ", ipNot=" + ipNot + ", time=" + time + ", timeEnd="
				+ timeEnd + ", timeNot=" + timeNot + ", orderBy=" + orderBy + "]";
	}

}
