package com.example.Event.Management.models;

public class LoginDTO {

    public String BITSid;
    public String password;

    public LoginDTO()
    {}
    public LoginDTO(String BITSid, String password){
        this.BITSid = BITSid;
        this.password = password;
    }

    public String getBITSid() {
        return BITSid;
    }

    public void setBITSid(String BITSid) {
        this.BITSid = BITSid;
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
                "BITSid='" + BITSid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
