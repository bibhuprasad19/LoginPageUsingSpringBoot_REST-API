package com.account.loginPage.service;

import com.account.loginPage.model.Account;
import com.account.loginPage.model.LoginCred;
import com.account.loginPage.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class AccountService  {
    @Autowired
    AccountRepository accRepo;

    public Account createAccount(Account acc){
        if(null!=accRepo.findByEmailId(acc.getEmailId()).get()){
           System.out.println("not valid email..Try again");
            return null;
        }
        else {
            return accRepo.save(acc);
        }
    }

    public Boolean isExisting(LoginCred loginCred){
        //Account acc;
        try {
            if(null==accRepo.findById(loginCred.getUsername()).get()){
                System.out.println("id doesn;t exists");
                return false;
            }
            Account acc =accRepo.findById(loginCred.getUsername()).get();
            if(acc.getUserName().equals(loginCred.getUsername()) && acc.getPassword().equals(loginCred.getPassword())){
                return true;
            }

        }
        catch(NoSuchElementException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
    public String details(String user){
        Account acc = accRepo.findById(user).get();
        return String.format("hello, "+acc.getName()+" , Your email address is "+acc.getEmailId());
    }
    public List<Account> findall(){
        return accRepo.findAll();
    }
    public String delete(String user,String password) {
        try {
            Account acc = accRepo.findById(user).get();
            if (null == acc.getUserName()) {
                return String.format("No such accout exists");
            }
            if (acc.getUserName().equals(user) && acc.getPassword().equals(password)) {
                accRepo.deleteById(user);
                return String.format("deleted successfully" + acc.getName());
            }
        }

        catch(NoSuchElementException e){
            System.out.println(e);
            return String.format("exception caught");
        }
        return "";
    }
}
