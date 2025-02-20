package com.example.bookhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan("com.example.bookhotel.domain")
@EnableJpaRepositories("com.example.bookhotel.repository")
@EnableAsync
@EnableCaching
public class BookhotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookhotelApplication.class, args);
	}

}
