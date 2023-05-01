package com.example.Event.Management.Repos;
import com.example.Event.Management.models.Calendar;

import com.example.Event.Management.models.Meets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Calendar addCalendar(Calendar calendar){
        return calendarRepository.save(calendar);
    }

    public Calendar updateCalendar(Calendar calendar){
        return calendarRepository.save(calendar);
    }

    public void deleteCalendar(String BitsId) {
        calendarRepository.deleteById(BitsId);
    }

    public Calendar findCalendarbyBITSId(String BITSid)
    {
        return calendarRepository.findCalendarByBITSid(BITSid);
    }


}
