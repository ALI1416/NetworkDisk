package com.ck.vo;

import com.ck.po.User;

/**
 * 用户值对象
 * 
 * @author ALI
 *
 */
public class UserVo {
	/**
	 * 用户持久层
	 */
	private User user;
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
	 * 新密码，不是数据库字段
	 */
	private String newPwd;
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
	 * 出生年结束，不是数据库字段
	 */
	private Integer yearEnd;
	/**
	 * 出生年否定，不是数据库字段
	 */
	private Integer yearNot;
	/**
	 * 描述。
	 */
	private String description;
	/**
	 * 类型。0已删除1未注册2管理员3教师4学生
	 */
	private Integer type;
	/**
	 * 单个文件最大容量。单位MB
	 */
	private Integer sizeone;
	/**
	 * 单个文件最大容量结束，不是数据库字段
	 */
	private Integer sizeoneEnd;
	/**
	 * 单个文件最大容量否定，不是数据库字段
	 */
	private Integer sizeoneNot;
	/**
	 * 最大容量。单位MB
	 */
	private Integer sizeall;
	/**
	 * 最大储存容量结束，不是数据库字段
	 */
	private Integer sizeallEnd;
	/**
	 * 最大储存容量否定，不是数据库字段
	 */
	private Integer sizeallNot;
	/**
	 * 当前使用容量。单位MB
	 */
	private Integer sizecurrent;
	/**
	 * 当前使用存储容量结束，不是数据库字段
	 */
	private Integer sizecurrentEnd;
	/**
	 * 当前使用存储容量否定，不是数据库字段
	 */
	private Integer sizecurrentNot;
	/**
	 * 注册时间
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
	/**
	 * 一对一。用户信息
	 */
	private UserInfoVo userInfoVo;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.id = user.getId();
		this.userinfoid = user.getUserinfoid();
		this.account = user.getAccount();
		this.pwd = user.getPwd();
		this.name = user.getName();
		this.gender = user.getGender();
		this.year = user.getYear();
		this.description = user.getDescription();
		this.type = user.getType();
		this.sizeone = user.getSizeone();
		this.sizeall = user.getSizeall();
		this.sizecurrent = user.getSizecurrent();
		this.time = user.getTime();
	}

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

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
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

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}

	public Integer getYearNot() {
		return yearNot;
	}

	public void setYearNot(Integer yearNot) {
		this.yearNot = yearNot;
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

	public Integer getSizeoneEnd() {
		return sizeoneEnd;
	}

	public void setSizeoneEnd(Integer sizeoneEnd) {
		this.sizeoneEnd = sizeoneEnd;
	}

	public Integer getSizeoneNot() {
		return sizeoneNot;
	}

	public void setSizeoneNot(Integer sizeoneNot) {
		this.sizeoneNot = sizeoneNot;
	}

	public Integer getSizeall() {
		return sizeall;
	}

	public void setSizeall(Integer sizeall) {
		this.sizeall = sizeall;
	}

	public Integer getSizeallEnd() {
		return sizeallEnd;
	}

	public void setSizeallEnd(Integer sizeallEnd) {
		this.sizeallEnd = sizeallEnd;
	}

	public Integer getSizeallNot() {
		return sizeallNot;
	}

	public void setSizeallNot(Integer sizeallNot) {
		this.sizeallNot = sizeallNot;
	}

	public Integer getSizecurrent() {
		return sizecurrent;
	}

	public void setSizecurrent(Integer sizecurrent) {
		this.sizecurrent = sizecurrent;
	}

	public Integer getSizecurrentEnd() {
		return sizecurrentEnd;
	}

	public void setSizecurrentEnd(Integer sizecurrentEnd) {
		this.sizecurrentEnd = sizecurrentEnd;
	}

	public Integer getSizecurrentNot() {
		return sizecurrentNot;
	}

	public void setSizecurrentNot(Integer sizecurrentNot) {
		this.sizecurrentNot = sizecurrentNot;
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

	public UserInfoVo getUserInfoVo() {
		return userInfoVo;
	}

	public void setUserInfoVo(UserInfoVo userInfoVo) {
		this.userInfoVo = userInfoVo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "UserVo [user=" + user + ", id=" + id + ", userinfoid=" + userinfoid + ", account=" + account + ", pwd="
				+ pwd + ", newPwd=" + newPwd + ", name=" + name + ", gender=" + gender + ", year=" + year + ", yearEnd="
				+ yearEnd + ", yearNot=" + yearNot + ", description=" + description + ", type=" + type + ", sizeone="
				+ sizeone + ", sizeoneEnd=" + sizeoneEnd + ", sizeoneNot=" + sizeoneNot + ", sizeall=" + sizeall
				+ ", sizeallEnd=" + sizeallEnd + ", sizeallNot=" + sizeallNot + ", sizecurrent=" + sizecurrent
				+ ", sizecurrentEnd=" + sizecurrentEnd + ", sizecurrentNot=" + sizecurrentNot + ", time=" + time
				+ ", timeEnd=" + timeEnd + ", timeNot=" + timeNot + ", orderBy=" + orderBy + ", userInfoVo="
				+ userInfoVo + "]";
	}

}
