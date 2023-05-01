package com.example.Event.Management;

import com.example.Event.Management.Repos.MeetingService;
import com.example.Event.Management.models.Meets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping
    public Meets addMeeting(@RequestBody Meets meets){
        return meetingService.addMeeting(meets);

    }

    @PutMapping
    public Meets updateMeeting(@RequestBody Meets meets) {
        return meetingService.updateMeeting(meets);
    }

    @DeleteMapping("/{meetingId}")
    public void deleteMeeting(@PathVariable String meetingId){
        meetingService.deleteMeeting(meetingId);
    }

    @GetMapping("/organiser/{organiser}")
    public List<Meets> findMeetingsByOrganizer(@PathVariable String OrganizerID){
        return findMeetingsByOrganizer(OrganizerID);
    }




}
