package com.ck.result;

public class Result {

	private Integer code;
	private String msg;
	private Object data;

	private Result() {
	}

	private Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Result ok() {
		Result result = new Result();
		result.setResultCode(ResultCode.OK);
		return result;
	}

	public static Result ok(Object data) {
		Result result = new Result();
		result.setResultCode(ResultCode.OK);
		result.setData(data);
		return result;
	}

	public static Result e(ResultCode resultCode) {
		Result result = new Result();
		result.setResultCode(resultCode);
		result.setMsg(resultCode.msg());
		return result;
	}

	public static Result e(ResultCode resultCode, Object data) {
		Result result = new Result();
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}

	public void setResultCode(ResultCode code) {
		this.code = code.code();
		this.msg = code.msg();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
