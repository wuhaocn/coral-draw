package com.mxgraph.server.biz.bean;

import java.io.Serializable;

/**
 * @Author bootdo 1992lcg@163.com
 */
public class DrawResult<T> implements Serializable {

	private int code;
	private String msg;
	private T data;

	public DrawResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public DrawResult(int code, T data) {

		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
