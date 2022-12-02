package com.account.loginPage.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="id")
    //private Long id;
    @Column(name="user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "email_id")
    private String emailId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }



}
