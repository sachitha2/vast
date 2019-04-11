package com.example.sandali.vast;

public class AccountModel {

    private String email;
    private int status;


    public AccountModel(){
        super();
    }

    public AccountModel(String email, int status){
        this.email = email;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
