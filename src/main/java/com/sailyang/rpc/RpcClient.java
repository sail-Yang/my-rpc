package com.sailyang.rpc;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yangfan
 * @version 1.0
 * @description: 将rpcRequest对象发送给服务端，接受返回的对象
 * @date 2024/9/10 20:44
 */
public class RpcClient {
    public static final Logger LOGGER = LogManager.getLogger(RpcClient.class);
    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        try (Socket socket = new Socket(host, port)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            // 确保所有缓冲的数据都被发送出去
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
