package com.green.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.green.dto.Article;
import com.green.dto.ArticleDTO;
import com.green.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	public List<Article> getList(){
		List<Article> articleList = articleRepository.findAll();
		
		return articleList;
	}

	public Article create(ArticleDTO articleDTO) {
		
		Article article = articleDTO.toEntity();
		// 입력된 id 가 있다면 (있으면 안됨, 자동증가)
		if(article.getId() != null)
			return null;
		
		Article saved = articleRepository.save(article);
		
		return saved;
	}
}