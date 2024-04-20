package id.my.hendisantika.springcloudeurekafeignclientintegrationtest;

import id.my.hendisantika.springcloudeurekafeignclientintegrationtest.model.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/21/24
 * Time: 06:07
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@RestController
@ActiveProfiles("eureka-test")
public class MockBookServiceConfig {

    @RequestMapping("/books")
    public List<Book> getBooks() {
        return Collections.singletonList(new Book("Hitchhiker's guide to the galaxy", "Douglas Adams"));
    }
}
