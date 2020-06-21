package com.mxgraph.server.biz.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author bootdo 1992lcg@163.com
 */
public class PageData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int count;
	private int code;
	private String msg;
	private List<?> data;

	public PageData(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public PageData(int count, int code, List<?> data) {
		this.count = count;
		this.code = code;
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
