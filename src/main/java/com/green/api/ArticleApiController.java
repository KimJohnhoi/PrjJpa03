package com.green.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.green.dto.Article;
import com.green.repository.ArticleRepository;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping(value="/api/articles")
	public List<Article> list(){
		List<Article> list = articleRepository.findAll();
		log.info("list", list);
		
		return list;
	}
	
}