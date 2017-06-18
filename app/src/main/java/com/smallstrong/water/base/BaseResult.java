package com.smallstrong.water.base;

/**
 *
 *@author smallstrong
 *created at 2017/6/18 下午4:55
 */
 
public class BaseResult<T> {

	private int code;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
