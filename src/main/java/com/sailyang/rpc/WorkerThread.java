package com.sailyang.rpc;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author yangfan
 * @version 1.0
 * @description: WorkerThread实现了Runnable接口，用于接收RpcRequest对象，解析并且调用，生成RpcResponse对象并传输回去。
 * @date 2024/9/10 20:55
 */
public class WorkerThread implements Runnable{
    public static final Logger LOGGER = LogManager.getLogger(WorkerThread.class);

    private Socket socket;
    private Object service;

    public WorkerThread(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            //接受客户端发来的请求实体
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //反射获取要执行的方法
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            //执行业务逻辑
            Object returnObject = method.invoke(service, rpcRequest.getParameters());
            //返回实体
            objectOutputStream.writeObject(RpcResponse.success(returnObject));
            objectOutputStream.flush();
        } catch (IOException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                 IllegalAccessException e) {
            LOGGER.error("调用或发送时有错误发生:",e);
        }
    }
}
