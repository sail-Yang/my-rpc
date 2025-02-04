package com.sailyang.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangfan
 * @version 1.0
 * @description: 服务端需要什么，才能唯一确定要调用的方法？
 * @date 2024/9/10 20:27
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    /**
     * 待调用接口名称
     */
    private String interfaceName;

    /**
     * 待调用方法名称
     */
    private String methodName;

    /**
     * 调用方法的参数
     */
    private Object[] parameters;

    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
