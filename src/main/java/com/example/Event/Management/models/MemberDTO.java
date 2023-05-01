package com.example.Event.Management.models;

public class MemberDTO {

    public String BITSid;
    public String name;
    private String password;

    public MemberDTO(){
    }

    public MemberDTO(String BITSid, String name, String password){

        this.BITSid = BITSid;
        this.name = name;
        this.password  = password;

    }

    public String getBITSid() {
        return BITSid;
    }

    public void setBITSid(String BITSid) {
        this.BITSid = BITSid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "BITSid='" + BITSid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
