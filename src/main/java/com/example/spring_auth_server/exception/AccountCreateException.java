package com.example.spring_auth_server.exception;

public class AccountCreateException extends RuntimeException{
public AccountCreateException(String info){
    super("Account create: " + info + " unsuccessful");
}
}
