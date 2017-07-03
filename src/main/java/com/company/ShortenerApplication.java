package com.company;

import com.company.impl.AccountDataServiceImpl;
import com.company.impl.RegisterDataServiceImpl;
import com.company.impl.StatisticDataServiceImpl;
import com.company.service.AccountDataService;
import com.company.service.RegisterDataService;
import com.company.service.StatisticDataService;
import com.company.util.CredentialsChecker;
import com.company.util.RandomStringGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShortenerApplication {
    @Bean
    AccountDataService accountDataService(){
        return new AccountDataServiceImpl();
    }
    @Bean
    RegisterDataService registerDataService() { return new RegisterDataServiceImpl(); }
    @Bean
    StatisticDataService statisticDataService() { return new StatisticDataServiceImpl(); }
    @Bean
	RandomStringGenerator randomStringGenerator() { return new RandomStringGenerator(); }
	@Bean
	CredentialsChecker credentialsChecker() { return new CredentialsChecker(); }

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
}
