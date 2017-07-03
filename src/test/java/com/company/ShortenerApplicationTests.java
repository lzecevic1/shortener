package com.company;

import com.company.impl.AccountDataServiceImpl;
import com.company.impl.RegisterDataServiceImpl;
import com.company.impl.StatisticDataServiceImpl;
import com.company.model.Account;
import com.company.model.LongURL;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ShortenerApplication.class)
public class ShortenerApplicationTests {

    @Mock
    AccountDataService accountDataService;
    @Mock
    RegisterDataService registerDataService;
    @Mock
    RandomStringGenerator randomStringGenerator;
    @Mock
    StatisticDataService statisticDataService;

    @Before
    public void setUp() throws Exception {
        randomStringGenerator = new RandomStringGenerator();
        statisticDataService = new StatisticDataServiceImpl();
        accountDataService = new AccountDataServiceImpl(randomStringGenerator, statisticDataService);
        registerDataService = new RegisterDataServiceImpl(randomStringGenerator);
    }

    @Test
    public void testAccountDataService_registerAccount() {
        Account account = new Account("Lejla");
        accountDataService.registerAccount(account);
        Assert.assertEquals(accountDataService.getAllAccounts().size(), 1);
        Assert.assertTrue(accountDataService.isRegisteredAccountID(account));
    }

    @Test
    public void testRegisterDataService_registerURL(){
        LongURL longURL = new LongURL("https://www.martinfowler.com/articles/mocksArentStubs.html", 301);
        String shortURL = registerDataService.getShortURL(longURL);
        Assert.assertEquals(longURL.getUrl(), registerDataService.getLongURLFromShort(shortURL).getUrl());
    }

    @Test
    public void testStatisticDataService_setStatistic(){
        statisticDataService.putNewAccount("Lejla");
        statisticDataService.setStatistic("Lejla", "https://www.martinfowler.com/articles/mocksArentStubs.html");
        Assert.assertEquals(statisticDataService.getStatistics("Lejla").size(), 1);
    }

}
