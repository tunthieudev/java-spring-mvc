package vn.hoidanit.laptopshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import vn.hoidanit.laptopshop.service.EmailService;

@SpringBootApplication
public class LaptopshopApplication {

	// @Autowired
	// private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(LaptopshopApplication.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
	// public void sendMail() {
	// emailService.sendEmail("tuan9e1@gmail.com",
	// "This is subject", "This is body of email");
	// }

}
