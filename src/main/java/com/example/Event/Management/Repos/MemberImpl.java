package com.example.Event.Management.Repos;

import com.example.Event.Management.Response.LoginMessage;
import com.example.Event.Management.models.Calendar;
import com.example.Event.Management.models.LoginDTO;
import com.example.Event.Management.models.Member;
import com.example.Event.Management.models.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class MemberImpl implements MemberService1{

    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public CalendarRepository calendarRepository;

    @Autowired public PasswordEncoder   passwordEncoder;

    @Override
    public String addMember(MemberDTO memberDTO){

        Member member = new Member( memberDTO.getBITSmail(), memberDTO.getName(), this.passwordEncoder.encode(memberDTO.getPassword()));
        Calendar calendar = new Calendar(member.getBITSmail()); //generated calenadars for all three members using
        calendar.generateCalendar(calendar.currentDate);
        memberRepository.save(member);
        calendarRepository.save(calendar);


        return member.getName();

    }
    public Member updateMember(Member member){
        return memberRepository.save(member);
    }
    public void deleteMember(String memberId){
        memberRepository.deleteById(memberId);
    }

    @Override
    public Member findMembersbyBITSmail(String BITSmail) {
        return memberRepository.findMemberByBITSmail(BITSmail);
    }

    MemberDTO memberDTO;
    @Override
    public LoginMessage loginMember(LoginDTO loginDTO){
        String msg = "";

        Member member1 = memberRepository.findMemberByBITSmail(loginDTO.getBITSmail());
        if(member1 != null){
            String password = loginDTO.getPassword();
            String encodedPassword = member1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if(isPwdRight) {
                Optional<Member> member = memberRepository.findOneByBITSmailAndPassword(loginDTO.getBITSmail(), encodedPassword);
                if (member.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);

                }
            }
            else{
                return new LoginMessage("password Incorrect", false);
            }
        } else {
            return new LoginMessage("User d.n.e", false);
        }

    }
}
