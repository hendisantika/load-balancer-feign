package id.my.hendisantika.springcloudeurekafeignclientintegrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudEurekaFeignClientIntegrationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaFeignClientIntegrationTestApplication.class, args);
	}

}
