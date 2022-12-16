package com.account.loginPage.service;

import com.account.loginPage.exception.DuplicateUserNameExistsException;
import com.account.loginPage.exception.DuplicateEmailIdExistsException;
import com.account.loginPage.model.Account;
import com.account.loginPage.model.LoginCred;
import com.account.loginPage.repository.AccountRepository;
import com.account.loginPage.exception.NoSuchAccountExistsException;
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
            Account isExisting =accRepo.findById(acc.getUserName()).orElse(null);
            if(isExisting!=null){
                throw new DuplicateUserNameExistsException("UserName already present in database..");
            }
            Account emailPresent =accRepo.findByEmailId(acc.getEmailId()).orElse(null);
            if(emailPresent!=null){
                throw new DuplicateEmailIdExistsException("Email id is not unique");
            }
            accRepo.save(acc);
            return acc;

    }

    public String isExisting(LoginCred loginCred){
        //Account acc;
        System.out.println(loginCred.getUsername()+" ---  "+loginCred.getPassword());
        Account acc =accRepo.findById(loginCred.getUsername()).orElse(null);
        System.out.println(acc.getUserName()+"  --  "+acc.getPassword());
            if(acc==null){
               throw new NoSuchAccountExistsException("No such account exists");
            }
            if(acc.getUserName().equals(loginCred.getUsername()) && acc.getPassword().equals(loginCred.getPassword())){
                return String.format(acc.getName()+acc.getEmailId());
        }
        return String.format("//hello//");
    }
    public String details(String user){
        Account acc = accRepo.findById(user).orElse(null);
        if(acc==null){
            throw new NoSuchAccountExistsException("No such user found");
        }
        else{
        return String.format("hello, "+acc.getName()+" , Your email address is "+acc.getEmailId());
    }
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

    public String updateAccountData(Account acc){
        System.out.println(acc.getUserName());
        Account account=accRepo.findById(acc.getUserName()).orElse(null);
        if(account==null){
            throw new NoSuchAccountExistsException("No Such Account Exists");
        }
        else {
            account.setName(acc.getName());
            account.setPassword(acc.getPassword());
            account.setEmailId(acc.getEmailId());
            account.setUserName(acc.getUserName());
            accRepo.save(account);
            return String.format("The account details updated successfully");
        }
    }
}
