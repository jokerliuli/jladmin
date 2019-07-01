package com.jokerliu.entity;


import com.jokerliu.enums.ResultStatusCode;
import lombok.Data;

/**
 * 移动端api接口返回的数据模型
 * @author jokerliu
 *
 */
@Data
public class Result {
	/**
	 * 返回的代码，200表示成功，其他表示失败
	 */
	private int code;
	/**
	 * 成功或失败时返回的错误信息
	 */
    private String msg;
	/**
	 * 成功时返回的数据信息
	 */
	private Object data;


	public Result(int code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(ResultStatusCode resultStatusCode, Object data){
		this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
	}

    public Result(int code, String msg){
    	this(code, msg, null);
	}

	public Result(ResultStatusCode resultStatusCode){
    	this(resultStatusCode, null);
	}


	public static Result ok(Object data) {
		return new Result(200,"OK",data);
	}


}
