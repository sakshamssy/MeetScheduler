package com.example.Event.Management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "Members") //store in mongodb as doc - one record in sql is one doc in mongodb 1 collection = table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id //primary key
    public String BITSid;
    public String name;
    private String password;
    //public Map<LocalDate, Map<LocalTime, Integer>> events;

    @DBRef
    public List<Meets> scheduledMeets;
    @DBRef
    public ArrayList<Meets> requestedMeets;

    //@DBRef
    //public Calendar calendar;

    public Member(String BITSid, String name, String password){
        this.BITSid = BITSid;
        this.name = name;
        this.password = password;
        this.scheduledMeets = new ArrayList<>();
        this.requestedMeets = new ArrayList<>();

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

    public List<Meets> getScheduledMeets() {
        return scheduledMeets;
    }

    public void setScheduledMeets(List<Meets> scheduledMeets) {
        this.scheduledMeets = scheduledMeets;
    }

    /*public Calendar getCalendar() {
        return calendar;
    }*/

  /*  public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    {
        this.events = calendar.generateCalendar(calendar.currentDate);
        return events;
    }*/

    @Override
    public String toString() {
        return "Member{" +
                "BITSid='" + BITSid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                //", events=" + events +
                ", scheduledMeets=" + scheduledMeets +
                //", calendar=" + calendar +
                '}';
    }
}
