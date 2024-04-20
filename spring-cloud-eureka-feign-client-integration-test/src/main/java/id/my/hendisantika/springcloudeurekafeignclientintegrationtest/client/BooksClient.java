package id.my.hendisantika.springcloudeurekafeignclientintegrationtest.client;

import id.my.hendisantika.springcloudeurekafeignclientintegrationtest.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/20/24
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
@FeignClient(name = "books-service")
public interface BooksClient {

    @RequestMapping("/books")
    List<Book> getBooks();
}
