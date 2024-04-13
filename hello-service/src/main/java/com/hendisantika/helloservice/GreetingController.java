package com.hendisantika.helloservice;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/13/24
 * Time: 07:58
 * To change this template use File | Settings | File Templates.
 */
public interface GreetingController {
    @RequestMapping("/greeting")
    String greeting();
}
