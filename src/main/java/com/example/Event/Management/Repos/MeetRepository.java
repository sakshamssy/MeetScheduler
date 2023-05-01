package com.example.Event.Management.Repos;

import com.example.Event.Management.models.Meets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MeetRepository extends MongoRepository<Meets, String>{
    List<Meets> findByOrganizerBITSidOrderByStartDateTimeAsc(String Id);

    Meets findMeetsByid(String Meetingid);

}
