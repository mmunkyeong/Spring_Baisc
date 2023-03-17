package com.ll.basic1.boundedContext.article.repository;

import com.ll.basic1.boundedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// ArticleRepository에서 article을 다룬다
// 이 클래스에서는 @Repository 어노테이션을 생략해도 된다
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
