package com.gilbut.gilbut.dto;

import com.gilbut.gilbut.entity.Article;

public class ArticleForm {
    private String title;
    private String content;

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {

        return new Article(null, title, content);
    }
}
