
package com.example.Event.Management.Repos;

        import com.example.Event.Management.models.Calendar;
        import com.example.Event.Management.models.Meets;
        import org.springframework.data.mongodb.repository.MongoRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;

        import java.time.LocalDate;
        import java.time.LocalTime;
        import java.util.List;
        import java.util.Map;

@Repository
@Transactional
public interface CalendarRepository extends MongoRepository<Calendar, String> {

    Calendar findCalendarByBITSmail(String bitSid);
}