package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);

		// Clearly FORCE Eureka to register explicitly with localhost IP
		Map<String, Object> props = new HashMap<>();
		props.put("eureka.instance.hostname", "localhost");
		props.put("eureka.instance.ip-address", "127.0.0.1");
		props.put("eureka.instance.prefer-ip-address", true);

		app.setDefaultProperties(props);
		app.run(args);
	}
}
