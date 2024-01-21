package com.gilbut.gilbut.controller;

import com.gilbut.gilbut.dto.ArticleForm;
import com.gilbut.gilbut.entity.Article;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        //1. DTO를 Entity로 변환
        Article article = form.toEntity();
        //2. repository로 Entity를 DB에 저장
        return "";
    }

}
