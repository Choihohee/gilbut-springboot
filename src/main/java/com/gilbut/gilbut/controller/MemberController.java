package com.gilbut.gilbut.controller;

import com.gilbut.gilbut.dto.MemberForm;
import com.gilbut.gilbut.entity.Member;
import com.gilbut.gilbut.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        //뷰 페이지 반환
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        //모든 데이터 가져오기
        List<Member> memberList = (List<Member>) memberRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("memberList", memberList);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 페이지 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        //뷰 페이지 설정하기
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm){
        log.info(memberForm.toString());
        //DTO를 엔티티로 변환
        Member member = memberForm.toEntity();
        log.info(member.toString());
        //엔티티를 DB에 저장
        Member target = memberRepository.findById(member.getId()).orElse(null);
        if (target != null){
            memberRepository.save(member);
        }
        //수정 결과 페이지로 리다이렉트
        return "redirect:/members/" + member.getId() ;
    }
}
