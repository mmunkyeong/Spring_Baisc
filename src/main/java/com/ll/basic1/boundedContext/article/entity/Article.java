package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // Entity라는 것을 알려줌
public class Article {
    @Id //primary key 설정
    @GeneratedValue(strategy = IDENTITY) //auto_increment
    private long id;
    private LocalDateTime createDate; // 데이터 생성 날짜
    private LocalDateTime  modifyDate; // 데이터 생성 날짜
    private String title;
    private String body;
}
