package com.example.Event.Management.Repos;

import com.example.Event.Management.Response.LoginMessage;
import com.example.Event.Management.models.LoginDTO;
import com.example.Event.Management.models.Member;
import com.example.Event.Management.models.MemberDTO;



public interface MemberService1 {

    String addMember(MemberDTO memberDTO);

    LoginMessage loginMember(LoginDTO loginDTO);

    public Member updateMember(Member member);

    public void deleteMember(String memberId);

    Member findMembersbyBITSmail(String BITSmail);
}
