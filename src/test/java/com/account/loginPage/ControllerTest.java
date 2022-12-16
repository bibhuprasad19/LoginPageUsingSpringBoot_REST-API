package com.account.loginPage;

import com.account.loginPage.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class ControllerTest {

    @Autowired
    AccountController accountController;

    @Test
    public void contextLoads() throws Exception{
        System.out.println("testing");
        assertThat(accountController).isNotNull();
    }
}
