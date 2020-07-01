package com.fanyitai.text.demo.util;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {
	
	private String result;
	private String message;
	private String secondMessage;
	private T data;
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String NO_MSG = "NO_MSG";
	public static final String NO_DATA = "NO_DATA";
	
	public static ResultEntity<String> successNoData() {
		return new ResultEntity<>(SUCCESS, NO_MSG,null, NO_DATA);
	}
	
	public static  ResultEntity<String> successWithData(Object data) {
		return new ResultEntity<>(SUCCESS, null, null, JSON.toJSONString(data));
	}
	
	public static <T> ResultEntity<T> failed(String message) {
		return new ResultEntity<>(FAILED, message,null, null);
	}

}