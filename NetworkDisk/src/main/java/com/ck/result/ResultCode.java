package com.ck.result;

public enum ResultCode {

	/* 成功 */
	/**
	 * 成功0
	 */
	OK(0, "成功"),

	/* 参数错误 101-199 */
	/**
	 * 参数缺失101
	 */
	PARAM_NOT_COMPLETE(101, "参数缺失"),
	/**
	 * 参数错误102
	 */
	PARAM_IS_ERROR(102, "参数不符合规范"),

	/* 系统错误：201-299 */
	/**
	 * 系统繁忙，请稍后重试201
	 */
	SYSTEM_INNER_ERROR(201, "系统繁忙，请稍后重试"),

	/* 非法操作：301-399 */
	/**
	 * 非法操作301
	 */
	INVALID_OPERATION(301, "非法操作"),

	/* 用户错误：1001-1999 */
	/**
	 * 用户未登录或登录已过期1001
	 */
	USER_NOT_LOGGED_IN(1001, "账号未登录或登录已过期"),
	/**
	 * 账号不存在或密码错误1002
	 */
	USER_LOGIN_ERROR(1002, "账号不存在或密码错误"),
	/**
	 * 账号已禁用或已注销1003
	 */
	USER_ACCOUNT_FORBIDDEN(1003, "账号已禁用或已注销"),
	/**
	 * 用户不存在1004
	 */
	USER_NOT_EXIST(1004, "账号不存在"),
	/**
	 * 用户已存在1005
	 */
	USER_HAS_EXISTED(1005, "账号已存在"),
	/**
	 * 实名认证信息不匹配或不存在1006
	 */
	USER_CERTIFICATION_ERROR(1006, "实名认证信息不匹配或不存在"),
	/**
	 * 实名认证已存在1007
	 */
	USER_CERTIFICATION_HAS_EXISTED(1007, "实名认证已存在"),

	/* 文件错误：2001-2999 */
	/**
	 * 文件为空2001
	 */
	FILE_IS_BLANK(2001, "文件为空"),
	/**
	 * 文件不存在2002
	 */
	FILE_NOT_EXIST(2002, "文件不存在"),
	/**
	 * 文件过大2003
	 */
	FILE_IS_TOO_LARGE(2003, "文件过大"),
	/**
	 * 需要登录2004
	 */
	FILE_HAS_LIMITED(2004, "需要登录"),
	/**
	 * 需要密码2005
	 */
	FILE_HAS_PASSWORD(2005, "需要密码"),
	/**
	 * 密码错误2006
	 */
	FILE_PASSWORD_ERROR(2006, "密码错误"),

	/* 容量错误 */
	/**
	 * 容量不足3001
	 */
	CAPACITY_IS_INSUFFICIENT(3001, "容量不足"),

	END(1, "尾部");
	private Integer code;

	private String msg;

	ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer code() {
		return this.code;
	}

	public String msg() {
		return this.msg;
	}

}