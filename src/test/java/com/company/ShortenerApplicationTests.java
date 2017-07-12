package com.company;

import com.company.impl.AccountDataServiceImpl;
import com.company.impl.RegisterDataServiceImpl;
import com.company.impl.StatisticDataServiceImpl;
import com.company.model.Account;
import com.company.model.LongUrl;
import com.company.service.AccountDataService;
import com.company.service.RegisterDataService;
import com.company.service.StatisticDataService;
import com.company.util.RandomStringGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ShortenerApplication.class)
public class ShortenerApplicationTests {

    @Mock
    private AccountDataService accountDataService;
    @Mock
    private RegisterDataService registerDataService;
    @Mock
    private RandomStringGenerator randomStringGenerator;
    @Mock
    private StatisticDataService statisticDataService;

    @Before
    public void setUp() throws Exception {
        randomStringGenerator = new RandomStringGenerator();
        statisticDataService = new StatisticDataServiceImpl();
        accountDataService = new AccountDataServiceImpl(randomStringGenerator);
        registerDataService = new RegisterDataServiceImpl(randomStringGenerator);
    }

    @Test
    public void testAccountDataService_RegisterAccount_NumberOfAccountsIs1() throws Exception {
        Account account = new Account("Lejla");
        accountDataService.registerAccount(account);
        Assert.assertEquals(accountDataService.getAllAccounts().size(), 1);
    }

    @Test
    public void testRegisterDataService_RegisterUrl_UrlIsPresent(){
        LongUrl longUrl = new LongUrl("https://www.martinfowler.com/articles/mocksArentStubs.html", 301);
        registerDataService.registerUrl(longUrl);
        Optional<String> shortURL = registerDataService.getShortURL(longUrl);
        Assert.assertTrue(shortURL.isPresent());
    }

    @Test
    public void testStatisticDataService_SetStatistic_NumberOfVisitsIs1(){
        statisticDataService.setStatistic("Lejla", "https://www.martinfowler.com/articles/mocksArentStubs.html");
        Assert.assertEquals(statisticDataService.getStatistics("Lejla").size(), 1);
    }
}
