package com.example.Event.Management.Repos;

import com.example.Event.Management.models.Meets;
import com.example.Event.Management.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
@Service
public class MeetingService {

    @Autowired
    private MeetRepository meetRepository;

    public Meets addMeeting(Meets meets){

       // ZonedDateTime utcDateTime = ZonedDateTime.of(LocalDateTime.of(meets.getDate(), meets.getStartTime()), ZoneId.of("UTC"));
        return meetRepository.save(meets);
    }

    public Meets updateMeeting(Meets meets) {
        return meetRepository.save(meets);
    }

    public void deleteMeeting(String meetingId) {
        meetRepository.deleteById(meetingId);
    }

    public List<Meets> findByOrganizerOrderByStartTimeAsc(String BITSmail){
        return meetRepository.findByOrganizerBITSmailOrderByStartDateTimeAsc(BITSmail);
    }

    public Meets findMeetsbyid(String Meetingid){
        return meetRepository.findMeetsByid(Meetingid);
    }

}
