package vn.hoidanit.laptopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// @SpringBootApplication
//include >< exclude
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class LaptopshopApplication {

	public static void main(String[] args) {

		// container
		ApplicationContext hoidanit = SpringApplication.run(LaptopshopApplication.class, args);
		for (String s : hoidanit.getBeanDefinitionNames()) {
			System.out.println(s);
		}
	}

}
