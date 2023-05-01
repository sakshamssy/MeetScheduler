package com.example.Event.Management.Repos;

import com.example.Event.Management.Response.LoginMessage;
import com.example.Event.Management.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostMapping(path = "/save")
    public String saveMember(@RequestBody MemberDTO memberDTO)
    {
        String id = memberService1.addMember(memberDTO);
        return id;
    }

    @GetMapping("/BITSmail/{BITSmail}")
    public Member findMembersByBITSmail(@PathVariable String BITSmail){
        return  memberService1.findMembersbyBITSmail(BITSmail);
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

    @PutMapping("/BITSmail/{BITSmail}/SetAvailibility/{Date}/{Time}")
    public Calendar SetAvailibility(@PathVariable String BITSmail, @PathVariable String Date, @PathVariable String Time) {
        LocalDate date = LocalDate.parse(Date);
        LocalTime time = LocalTime.parse(Time);
        //LocalTime time = LocalTime.parse(Objects.requireNonNull(Time));
        Calendar calendar = calendarService.findCalendarbyBITSId(BITSmail);
        //calendar.events = calendar.getcal(BITSmail);


        Map<LocalDate, Map<LocalTime, Integer>> cal = calendar.getcal(BITSmail);
        int value = cal.get(date).get(time);

        //System.out.println(time);
        //System.out.println(value);
        if (value == 0) {
            cal.get(date).put(time,1);
            calendar.setcal(cal);
        }
        return calendarService.updateCalendar(calendar);
    }

    @PutMapping("/BITSmail/{BITSmail1}/IssueRequest/BITSmail/{BITSmail2}/{Date}/{Time}")
    public Member IssueRequest(@PathVariable String BITSmail1,@PathVariable String BITSmail2, @PathVariable String Date, @PathVariable String Time){
        //ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        //DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSXXX");
        LocalDate date = LocalDate.parse(Date);
        LocalTime time = LocalTime.parse(Time);
        time = time.plusHours(5).plusMinutes(30);
        //ZonedDateTime Dat = ZonedDateTime.of(date, time, zoneId);
        //ZonedDateTime tim = ZonedDateTime.of(date, time, zoneId);
        //LocalDate DATE = Dat.toLocalDate(); // convert to LocalDate
        //LocalTime TIME = tim.toLocalTime(); // convert to LocalTime




        Calendar calendar = calendarService.findCalendarbyBITSId(BITSmail2);
        // prof calendar obtained
        Member organizer = memberService1.findMembersbyBITSmail(BITSmail2);
        Member attendee = memberService2.findMembersbyBITSmail(BITSmail1);

        Meets meet = new Meets(date,time,organizer,attendee);
        meetingService.addMeeting(meet);

        //organizer.requestedMeets.add(meet);

        ArrayList<Meets> requestedMeets1 = organizer.getRequestedMeets();
        requestedMeets1.add(meet);
        organizer.setRequestedMeets(requestedMeets1);

        return memberService1.updateMember(organizer);

        //organizer.setScheduledMeets(scheduledMeets1);
        //scheduledMeets1.add(meet);
        //requestedMeets1.remove(meet);
        //meet.setAttendee(attendee);
       //return memberService1.findMembersbyBITSmail(BITSmail2);
        //return organizer;
    }

    @PutMapping("/BITSmail/{BITSmail}/RequestedMeets/{MeetingId}/{B}")
    public Member AcceptRequest(@PathVariable String BITSmail,@PathVariable String MeetingId , @PathVariable int B) {

        Calendar calendar = calendarService.findCalendarbyBITSId(BITSmail);
        // prof calendar obtained
        Member member = memberService1.findMembersbyBITSmail(BITSmail);
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

        Map<LocalDate, Map<LocalTime, Integer>> cal = calendar.getcal(BITSmail);
        
        if (B==1) {
            //if (calendar.events.get(date).get(time) == 0) {
                cal.get(date).put(t, 1);
                calendar.setcal(cal);

            calendarService.updateCalendar(calendar);
            Member attendee = meet.getAttendee();
            List<Meets> scheduledMeets = attendee.getScheduledMeets();
            ArrayList<Meets> requestedMeets = attendee.getRequestedMeets();
            scheduledMeets.add(meet);
            attendee.setRequestedMeets(requestedMeets);
            attendee.setScheduledMeets(scheduledMeets);

            requestedMeets.remove(meet);
            meet.setAttendee(attendee);

            Member organizer = meet.getOrganizer();
            List<Meets> scheduledMeets1 = organizer.getScheduledMeets();
            ArrayList<Meets> requestedMeets1 = organizer.getRequestedMeets();
            organizer.setRequestedMeets(requestedMeets1);
            organizer.setScheduledMeets(scheduledMeets1);
            scheduledMeets1.add(meet);
            requestedMeets1.remove(meet);
            meet.setAttendee(attendee);
                //member.requestedMeets.remove(meet);
                //Member member2 = meet.getAttendee();
            Calendar calendar2 = calendarService.findCalendarbyBITSId(meet.getAttendee().getBITSmail());
            Map<LocalDate, Map<LocalTime, Integer>> cal2 = calendar2.getcal(BITSmail);

            cal2.get(date).put(t, 1);
            calendar2.setcal(cal2);

            calendarService.updateCalendar(calendar2);

            memberService1.updateMember(organizer);
            memberService1.updateMember(attendee);
            member = organizer;
        }

        /*for (int i = 0; i < member.requestedMeets.size(); i++) {
            if (member.requestedMeets.get(i).id == MeetingId) {
                member.requestedMeets.remove(meet);
            }

        }*/


        //memberService1.updateMember(meet.attendee);
        //memberService1.updateMember(meet.organizer);

        return memberService1.updateMember(member);
        //return memberService1.findMembersbyBITSmail(BITSmail2);
        //return organizer;
    }

    @PutMapping("/BITSmail/{BITSmail}/ChangePassword")
    public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, @PathVariable String BITSmail)
    {
        Member member = memberService1.findMembersbyBITSmail(BITSmail);
        String pwd = member.getPassword();
        Boolean isPwdRight = passwordEncoder.matches(currentPassword, pwd);
        if(isPwdRight)
        {
            member.setPassword(newPassword);
            memberService1.updateMember(member);
        }
        else {
            return "dghfh";
            //return member.getPassword();
        }
        return member.getPassword();
    }
    


}




