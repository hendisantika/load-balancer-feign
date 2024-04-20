package id.my.hendisantika.springcloudeurekafeignclient;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/21/24
 * Time: 06:17
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class NoFeignClientController {

    private static final String SERVICE_NAME = "spring-cloud-eureka-client";

    @Autowired
    private EurekaClient eurekaClient;
}
