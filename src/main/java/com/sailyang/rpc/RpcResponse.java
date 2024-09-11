package com.sailyang.rpc;

import com.sailyang.common.ResponseCode;
import lombok.Data;

import java.io.Serializable;
import java.net.ResponseCache;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/9/10 20:30
 */
@Data
public class RpcResponse<T> implements Serializable {
    /**
     * 响应状态码
     */
    private Integer statusCode;

    /**
     * 响应状态补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.FAIL.getCode());
        response.setData(data);
        return response;
    }
}
