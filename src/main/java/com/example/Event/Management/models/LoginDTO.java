package com.example.Event.Management.models;

public class LoginDTO {

    public String BITSmail;
    public String password;

    public LoginDTO()
    {}
    public LoginDTO(String BITSmail, String password){
        this.BITSmail = BITSmail;
        this.password = password;
    }

    public String getBITSmail() {
        return BITSmail;
    }

    public void setBITSmail(String BITSmail) {
        this.BITSmail = BITSmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "BITSmail='" + BITSmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
