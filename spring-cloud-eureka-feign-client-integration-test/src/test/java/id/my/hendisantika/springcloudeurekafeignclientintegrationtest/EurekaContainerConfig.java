package id.my.hendisantika.springcloudeurekafeignclientintegrationtest;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : load-balancer-feign
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/21/24
 * Time: 06:01
 * To change this template use File | Settings | File Templates.
 */
@TestConfiguration
@ActiveProfiles("eureka-test")
public class EurekaContainerConfig {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public static GenericContainer eurekaServer
                = new GenericContainer("springcloud/eureka")
                .withExposedPorts(8761);

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {

            Startables.deepStart(Stream.of(eurekaServer)).join();

            TestPropertyValues
                    .of("eureka.client.serviceUrl.defaultZone=http://localhost:"
                            + eurekaServer.getFirstMappedPort().toString()
                            + "/eureka")
                    .applyTo(configurableApplicationContext);
        }
    }
}
