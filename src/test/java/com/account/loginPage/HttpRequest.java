package com.account.loginPage;

import com.account.loginPage.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void TestingWebApplication() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/app/",
                String.class)).contains("this is the home page of bibhu prasad");
    }

    @Test
    public void Mockstrying(){
        AccountService accService = mock(AccountService.class);

        when(accService.details("dummy")).thenReturn("data is there working fine");

        String dataReceived = accService.details("dummy");
        System.out.println(dataReceived);
        assertEquals("data is there working finer",dataReceived);
    }

}
