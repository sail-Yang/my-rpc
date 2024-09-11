import com.sailyang.rpc.RpcServer;
import com.sailyang.service.HelloService;
import com.sailyang.service.HelloServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/9/10 21:02
 */
public class TestServer {
    // 定义日志记录器对象

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
