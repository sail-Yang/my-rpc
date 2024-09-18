import com.sailyang.rpc.api.HelloObject;
import com.sailyang.rpc.api.HelloService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yangfan
 * @version 1.0
 * @description: 服务端实现
 * @date 2024/9/10 20:22
 */
//@Slf4j
public class HelloServiceImpl implements HelloService {
    public static final Logger LOGGER = LogManager.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        LOGGER.info("recevice : {}",object.getMessage());
        return "这是调用的返回值, id=" + object.getId();
    }
}
