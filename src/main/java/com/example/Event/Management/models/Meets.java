package com.example.Event.Management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Document(collection = "Meetings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meets {

    @Id
    public String id;
    public LocalDateTime startDateTime;
    public LocalDate/*Time*/ Date;

    @DBRef
    private Member organizer;

    @DBRef
    private Member attendee;

    public Meets(LocalDate/*Time*/ date, LocalTime startTime, Member organizer, Member attendee) {

        this.Date=date;
        this.startDateTime = LocalDateTime.of(date, startTime).atZone(ZoneId.of("Asia/Kolkata")).toLocalDateTime();
       // this.startDateTime = startTime;
        this.organizer = organizer;
        this.attendee = attendee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
/*
    public LocalDateTime getStartTime() {
        return startDateTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startDateTime = startTime;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }
*/
    public Member getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Member organizer) {
        this.organizer = organizer;
    }

    public Member getAttendee() {
        return attendee;
    }

    public void setAttendee(Member attendee) {
        this.attendee = attendee;
    }
}
