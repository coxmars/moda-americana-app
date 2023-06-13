package com.app.moda.americana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env.properties")
public class ModaAmericanaAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModaAmericanaAlphaApplication.class, args);
	}

}
