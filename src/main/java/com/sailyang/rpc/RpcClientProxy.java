package com.sailyang.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangfan
 * @version 1.0
 * @description: 客户端-JDK动态代理
 * @date 2024/9/10 20:37
 */
public class RpcClientProxy implements InvocationHandler {
    private String host;

    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    /**
     * 指明代理对象的方法被调用时的动作
     * 这里生成一个RpcRequest对象，然后发送出去，返回从服务端接收到的结果
     *
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        RpcClient rpcClient = new RpcClient();
        return ((RpcResponse) rpcClient.sendRequest(rpcRequest,host,port)).getData();

    }
}
