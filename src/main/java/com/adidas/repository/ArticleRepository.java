package com.adidas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.adidas.domain.Article;

@RestResource(path="articles", rel="article")
public interface ArticleRepository extends CrudRepository<Article,Long>{

}
