package com.gilbut.gilbut.controller;

import com.gilbut.gilbut.dto.MemberForm;
import com.gilbut.gilbut.entity.Member;
import com.gilbut.gilbut.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newMemberForm(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm memberform){
        log.info(memberform.toString());

        Member member = memberform.toEntity();    //DTO를 Entity로 변환
        log.info(member.toString());

        Member saved = memberRepository.save(member);   //repository로 Entity를 DB에 저장
        log.info(saved.toString());
        return "";
    }
}
