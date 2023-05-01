package com.example.Event.Management.Repos;

import com.example.Event.Management.Response.LoginMessage;
import com.example.Event.Management.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("api/v1/member")
public class MemberController1 {

    @Autowired
    private MemberService1 memberService1;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MeetingService meetingService;


    @Autowired
    private MemberService1 memberService2;
    @Autowired
    CalendarService calendarService;

    @PostMapping(path = "/save")
    public String saveMember(@RequestBody MemberDTO memberDTO)
    {
        String id = memberService1.addMember(memberDTO);
        return id;
    }

    @GetMapping("/BITSid/{BITSid}")
    public Member findMembersByBITSid(@PathVariable String BITSid){
        return  memberService1.findMembersbyBITSid(BITSid);
    }

    @DeleteMapping("/{memberID}")
    public void deleteMember(@PathVariable String memberID){
        memberService1.deleteMember(memberID);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginDTO loginDTO)
    {
        LoginMessage loginMessage = memberService1.loginMember(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

    @PutMapping("/BITSid/{BITSid}/SetAvailibility/{Date}/{Time}")
    public Calendar SetAvailibility(@PathVariable String BITSid, @PathVariable String Date, @PathVariable String Time) {
        LocalDate date = LocalDate.parse(Date);
        LocalTime time = LocalTime.parse(Time);
        //LocalTime time = LocalTime.parse(Objects.requireNonNull(Time));
        Calendar calendar = calendarService.findCalendarbyBITSId(BITSid);
        //calendar.events = calendar.getcal(BITSid);
        int value = calendar.events.get(date).get(time);
        //System.out.println(time);
        //System.out.println(value);
        if (value == 0) {
            calendar.events.get(date).put(time,1);
        }
        return calendarService.updateCalendar(calendar);
    }

    @PutMapping("/BITSid/{BITSid1}/IssueRequest/BITSid/{BITSid2}/{Date}/{Time}")
    public Member IssueRequest(@PathVariable String BITSid1,@PathVariable String BITSid2, @PathVariable String Date, @PathVariable String Time){
        //ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSXXX");
        LocalDate date = LocalDate.parse(Date);
        LocalTime time = LocalTime.parse(Time);
        time = time.plusHours(5).plusMinutes(30);
        //ZonedDateTime Dat = ZonedDateTime.of(date, time, zoneId);
        //ZonedDateTime tim = ZonedDateTime.of(date, time, zoneId);
        //LocalDate DATE = Dat.toLocalDate(); // convert to LocalDate
        //LocalTime TIME = tim.toLocalTime(); // convert to LocalTime




        Calendar calendar = calendarService.findCalendarbyBITSId(BITSid2);
        // prof calendar obtained
        Member organizer = memberService1.findMembersbyBITSid(BITSid2);
        Member attendee = memberService2.findMembersbyBITSid(BITSid1);

        Meets meet = new Meets(date,time,organizer,attendee);
        meetingService.addMeeting(meet);
        organizer.requestedMeets.add(meet);
       return memberService1.updateMember(organizer);
       //return memberService1.findMembersbyBITSid(BITSid2);
        //return organizer;
    }

    @PutMapping("/BITSid/{BITSid}/RequestedMeets/{MeetingId}/{B}")
    public Member AcceptRequest(@PathVariable String BITSid,@PathVariable String MeetingId , @PathVariable int B) {

        Calendar calendar = calendarService.findCalendarbyBITSId(BITSid);
        // prof calendar obtained
        Member member = memberService1.findMembersbyBITSid(BITSid);
        Meets meet = meetingService.findMeetsbyid(MeetingId);
       // LocalDate date = meet.startDateTime.toLocalDate();
       // LocalTime time = meet.startDateTime.toLocalTime();
        //String T = String.parse(time);
        LocalDate date = meet.startDateTime.toLocalDate();
        LocalTime time = meet.startDateTime.toLocalTime();
        time = time.minusHours(5).minusMinutes(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
        LocalTime t = LocalTime.parse(formattedTime);

        if (B==1) {
            //if (calendar.events.get(date).get(time) == 0) {
                calendar.events.get(date).put(t, 1);
                calendarService.updateCalendar(calendar);
                meet.attendee.scheduledMeets.add(meet);
                meet.organizer.requestedMeets.remove(meet);
                member.scheduledMeets.add(meet);
                //member.requestedMeets.remove(meet);
                //Member member2 = meet.getAttendee();
            Calendar calendar2 = calendarService.findCalendarbyBITSId(meet.attendee.getBITSid());
            calendar2.events.get(date).put(t, 1);
            calendarService.updateCalendar(calendar2);

            memberService1.updateMember(member);
            memberService1.updateMember(meet.attendee);

        }

        /*for (int i = 0; i < member.requestedMeets.size(); i++) {
            if (member.requestedMeets.get(i).id == MeetingId) {
                member.requestedMeets.remove(meet);
            }

        }*/


        //memberService1.updateMember(meet.attendee);
        //memberService1.updateMember(meet.organizer);

        return memberService1.updateMember(member);
        //return memberService1.findMembersbyBITSid(BITSid2);
        //return organizer;
    }

}




