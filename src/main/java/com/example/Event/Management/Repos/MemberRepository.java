package com.example.Event.Management.Repos;

import com.example.Event.Management.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
@Transactional
@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Member findMemberByBITSmail(String BITSmail);

    Optional<Member> findOneByBITSmailAndPassword(String BITSmail, String password);


}
