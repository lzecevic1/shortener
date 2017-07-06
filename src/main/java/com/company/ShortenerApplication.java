package com.company;

import com.company.impl.H2AccountDataServiceImpl;
import com.company.impl.H2RegisterDataServiceImpl;
import com.company.impl.H2StatisticDataServiceImpl;
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
        return new H2AccountDataServiceImpl();
    }
    @Bean
    RegisterDataService registerDataService() { return new H2RegisterDataServiceImpl(); }
    @Bean
    StatisticDataService statisticDataService() { return new H2StatisticDataServiceImpl(); }
    @Bean
	RandomStringGenerator randomStringGenerator() { return new RandomStringGenerator(); }
	@Bean
	CredentialsChecker credentialsChecker() { return new CredentialsChecker(); }

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
}
