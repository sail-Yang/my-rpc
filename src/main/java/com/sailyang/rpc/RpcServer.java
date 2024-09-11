package com.sailyang.rpc;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author yangfan
 * @version 1.0
 * @description: 监听端口，轮询，有请求就建立一个线程，处理调用
 * @date 2024/9/10 20:49
 */
public class RpcServer {
    private final ExecutorService threadPool;

    public static final Logger LOGGER = LogManager.getLogger(RpcServer.class);

    public RpcServer() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    //暂时只能注册一个接口，注册后立刻开始监听
    public void register(Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("service is running ...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                LOGGER.info("client connected! Ip is : "+socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service));
            }
        } catch (IOException e) {
            LOGGER.error("连接时有错误发生：",e);
        }
    }
}
