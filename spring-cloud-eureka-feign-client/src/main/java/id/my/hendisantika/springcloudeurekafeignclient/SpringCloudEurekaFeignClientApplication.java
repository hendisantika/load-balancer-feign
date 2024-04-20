package id.my.hendisantika.springcloudeurekafeignclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableFeignClients
@Controller
public class SpringCloudEurekaFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaFeignClientApplication.class, args);
	}

	@Autowired
	private GreetingClient greetingClient;

	@GetMapping("/get-greeting")
	public String greeting(Model model) {
		model.addAttribute("greeting", greetingClient.greeting());
		return "greeting-view";
	}
}
