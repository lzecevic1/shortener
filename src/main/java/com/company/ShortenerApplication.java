package com.company;

import com.company.Helper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShortenerApplication {

    @Bean
    RandomStringGenerator randomStringGenerator() { return new RandomStringGenerator(); }
	@Bean
	AccountHelper accountHelper() { return new AccountHelper(); }
	@Bean
    RegisterHelper registerHelper() { return new RegisterHelper(); }
    @Bean
	StatisticHelper statisticHelper() { return new StatisticHelper(); }
    @Bean
    CredentialsChecker credentialsChecker() { return new CredentialsChecker(); }

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
}
