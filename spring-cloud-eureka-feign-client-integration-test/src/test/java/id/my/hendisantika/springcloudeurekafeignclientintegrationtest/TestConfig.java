package id.my.hendisantika.springcloudeurekafeignclientintegrationtest;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/20/24
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
@TestConfiguration
@ActiveProfiles("test")
public class TestConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockBooksService() {
        return new WireMockServer(options().port(1030));
    }

    @Bean(name = "secondMockBooksService", initMethod = "start", destroyMethod = "stop")
    public WireMockServer secondBooksMockService() {
        return new WireMockServer(options().port(1031));
    }
}
