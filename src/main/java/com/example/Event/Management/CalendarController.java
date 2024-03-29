package com.example.Event.Management;

import com.example.Event.Management.Repos.CalendarService;
import com.example.Event.Management.models.Calendar;
import com.example.Event.Management.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCalendar(@RequestBody Calendar calendar) {
        Calendar savedCalendar = calendarService.addCalendar(calendar);
        return ResponseEntity.ok(savedCalendar);
    }

    @GetMapping("/BITSmail/{BITSmail}")
    public Calendar findCalnedarByBITSmail(@PathVariable String BITSmail){
        return  calendarService.findCalendarbyBITSId(BITSmail);
    }
/*
    @PutMapping("/BITSmail/{BITSmail}/SetAvailibility/{Date}/{Time}")
    public Calendar SetAvailibility(@PathVariable String BITSmail,@PathVariable String Date,@PathVariable String Time){
        LocalDate date = LocalDate.parse(Date);
        LocalTime time = LocalTime.parse(Time);
        //LocalTime time = LocalTime.parse(Objects.requireNonNull(Time));
        Calendar calendar = calendarService.findCalendarbyBITSId(BITSmail);
        //calendar.events = calendar.getcal(BITSmail);
        int value = calendar.events.get(date).get(time);
        //System.out.println(time);
        //System.out.println(value);
        if(value==0) {
            calendar.events.get(date).put(time, 1);
        }
        return calendarService.updateCalendar(calendar);
    }*/

   // @PostMapping("/BITSmail1/{BITSmail1}/IssueRequest/BITSmail2/{BITSmail2}/{Date}/{Time}")
    //public Calendar IssueRe(@PathVariable String BITSmail,@PathVariable String Date,@PathVariable String Time){



    }
