package com.eddie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties","classpath:reCaptcha.properties"})
public class TryRecaptchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryRecaptchaApplication.class, args);
	}
}
