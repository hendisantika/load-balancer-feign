package id.my.hendisantika.springcloudeurekafeignclientintegrationtest;

import com.github.tomakehurst.wiremock.WireMockServer;
import id.my.hendisantika.springcloudeurekafeignclientintegrationtest.client.BooksClient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/21/24
 * Time: 06:05
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@EnableFeignClients
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {TestConfig.class})
class LoadBalancerIntegrationTest {

    @Autowired
    private LoadBalancerClientFactory clientFactory;

    @Autowired
    private BooksClient booksClient;

    @Autowired
    private WireMockServer mockBooksService;

    @Autowired
    private WireMockServer secondMockBooksService;
}
