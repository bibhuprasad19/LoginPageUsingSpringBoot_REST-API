package com.account.loginPage.controller;

import com.account.loginPage.model.LoginCred;
import com.account.loginPage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import com.account.loginPage.model.Account;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AccountController {
    @Autowired
    AccountService accService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String defaultmethod(){
        return String.format("this is the home page of bibhu prasad");
    }


    @RequestMapping(value = "/create",method= RequestMethod.POST)
    public Account createAccount(@RequestBody Account account){
        System.out.println("   username --  "+account.getName()+"  --  "+account.getPassword()+"  --  "+account.getUserName()+"  --  "+account.getEmailId());
            if (account.getUserName() == null) {
                System.out.println("userName cannot be empty");
                return account;
            } else {
                return accService.createAccount(account);
            }

    }

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String loginAccount(@RequestBody LoginCred loginCred){
        Boolean loginOrNot = accService.isExisting(loginCred);
        if(loginOrNot){
            return String.format("hello, user Login was successful");
        }
        return String.format("Incorrect Credentials.. Please try with correct credentials");
    }

    @RequestMapping(value = "/accounts",method = RequestMethod.GET)
    public List<Account> allAccounts(){
        return accService.findall();
    }


    @RequestMapping(value = "/account/{username}/{password}",method = RequestMethod.DELETE)
    public void deletion(@PathVariable String username,@PathVariable String password){
        accService.delete(username,password);
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateAccount(@RequestBody Account account){
        String str=accService.updateAccountData(account);
        return String.format(str);
    }
}
