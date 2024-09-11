import com.sailyang.entity.HelloObject;
import com.sailyang.rpc.RpcClientProxy;
import com.sailyang.service.HelloService;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/9/10 21:03
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(13, "a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
