package com.eugene.customerms;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomermsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomermsApplication.class, args);
	}

	@Value("${friend-familyms.url}")
	private String friendFamilyUrl;

	@PostConstruct
	public void logConfig() {
		System.out.println("🚨 friend-familyms.url = " + friendFamilyUrl);
	}

}
