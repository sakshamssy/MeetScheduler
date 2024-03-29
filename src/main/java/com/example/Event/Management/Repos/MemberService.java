package com.example.Event.Management.Repos;

import com.example.Event.Management.models.Member;
import com.example.Event.Management.models.Meets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member){
        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        return memberRepository.save(member);
    }

    public void deleteMember(String memberId){
        memberRepository.deleteById(memberId);
    }

    public Member findMembersbyBITSmail(String BITSmail){
        return memberRepository.findMemberByBITSmail(BITSmail);
    }

    public void issue_request(String BITSmail,Member member,Meets meeting){

    }

}
