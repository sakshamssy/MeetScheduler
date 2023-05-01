package com.example.Event.Management.models;

public class MemberDTO {

    public String BITSmail;
    public String name;
    private String password;

    public MemberDTO(){
    }

    public MemberDTO(String BITSmail, String name, String password){

        this.BITSmail = BITSmail;
        this.name = name;
        this.password  = password;

    }

    public String getBITSmail() {
        return BITSmail;
    }

    public void setBITSmail(String BITSmail) {
        this.BITSmail = BITSmail;
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
                "BITSmail='" + BITSmail + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
