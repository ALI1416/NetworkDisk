package com.ck.vo;

/**
 * 用户信息持久层
 * 
 * @author ALI
 *
 */
public class UserInfoVo {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 学号或工号
	 */
	private String number;
	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 类型。0已删除1未注册2管理员3教师4学生
	 */
	private Integer type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserInfoVo [id=" + id + ", name=" + name + ", number=" + number + ", idcard=" + idcard + ", type="
				+ type + ", orderBy=" + orderBy + "]";
	}

}
