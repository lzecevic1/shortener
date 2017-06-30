package com.company;

import com.company.Impl.AccountDataServiceImpl;
import com.company.Impl.RegisterDataServiceImpl;
import com.company.Impl.StatisticDataServiceImpl;
import com.company.Interface.AccountDataService;
import com.company.Interface.RegisterDataService;
import com.company.Interface.StatisticDataService;
import com.company.Util.CredentialsChecker;
import com.company.Util.RandomStringGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShortenerApplication {
    @Bean
    AccountDataService accountInterface() { return new AccountDataServiceImpl(); }
    @Bean
    RegisterDataService registerInterface() { return new RegisterDataServiceImpl(); }
    @Bean
    StatisticDataService statisticInterface() { return new StatisticDataServiceImpl(); }
    @Bean
	RandomStringGenerator randomStringGenerator() { return new RandomStringGenerator(); }
	@Bean
	CredentialsChecker credentialsChecker() { return new CredentialsChecker(); }

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
}
