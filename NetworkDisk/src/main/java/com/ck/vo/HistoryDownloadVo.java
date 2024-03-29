package com.ck.vo;

/**
 * 下载历史记录值对象
 * 
 * @author ALI
 *
 */
public class HistoryDownloadVo {
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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

	@Override
	public String toString() {
		return "HistoryDownloadVo [id=" + id + ", userid=" + userid + ", fileid=" + fileid + ", ip=" + ip + ", ipEnd="
				+ ipEnd + ", ipNot=" + ipNot + ", time=" + time + ", timeEnd=" + timeEnd + ", timeNot=" + timeNot
				+ ", orderBy=" + orderBy + "]";
	}

}
