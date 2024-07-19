package com.alexlasota.nbp_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NbpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbpDemoApplication.class, args);
	}

}
