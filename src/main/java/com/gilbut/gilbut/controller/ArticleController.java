package com.gilbut.gilbut.controller;

import com.gilbut.gilbut.dto.ArticleForm;
import com.gilbut.gilbut.entity.Article;
import com.gilbut.gilbut.repository.ArticleRepository;
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
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    //article 게시
    @GetMapping("/articles/new")
    public String newArticleForm() {    //새로운 article 생성하는 화면
        return "articles/new";
    }

    //입력한 article 데이터 저장
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { //새로운 article을 받아서 DB에 저장하는 코드
        log.info(form.toString());
        //1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //2. repository로 Entity를 DB에 저장
        Article saved = articleRepository.save(article);    //save()는 crudRepository에서 제공하는 기능
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    //articles id로 개별조회
    @GetMapping("/articles/{id}")    //데이터 조회요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰페이지 반환
        return "articles/show";
    }

    //articles 목록조회
    @GetMapping("/articles")
    public String index(Model model){
        //모든 데이터 가져오기
        List<Article> articleList = (List<Article>) articleRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("articleList",articleList);
        //뷰페이지 설정하기
        return "articles/index";
    }

    //articles id로 개별 수정
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 페이지 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }
    //articles id로 개별 수정 후 데이터 저장
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //엔티티를 DB에 저장하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);    //DB에서 기존 데이터 가져옴
        if(target != null){
            articleRepository.save(articleEntity);  //엔티티를 DB에 갱신
        }
        //수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }
}
