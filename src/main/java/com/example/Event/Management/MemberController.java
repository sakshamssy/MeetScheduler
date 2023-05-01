package com.example.Event.Management;

import com.example.Event.Management.Repos.MemberService;
import com.example.Event.Management.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {



    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);

    }

    @PutMapping
    public Member updateMember(@RequestBody Member member){
        return memberService.updateMember(member);
    }

    @DeleteMapping("/{memberID}")
    public void deleteMember(@PathVariable String memberID){
        memberService.deleteMember(memberID);

    }

    @GetMapping("/BITSmail/{BITSmail}")
    public Member findMembersByBITSmail(@PathVariable String BITSmail){
        return  memberService.findMembersbyBITSmail(BITSmail);
    }
}
