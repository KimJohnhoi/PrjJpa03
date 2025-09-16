package com.green.api;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.green.dto.Article;
import com.green.dto.ArticleDTO;
import com.green.service.ArticleService;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleService articleService;
	
	// 결과 data 로 출력: JSON 기본값
	@GetMapping(value="/api/articles", produces = "application/json;charset=UTF-8")
	public List<Article> list(){
		List<Article> list = articleService.getList();
		log.info("list", list);
		
		return list;
	}
	
	// ResponseEntity<Article> : Article + ResponseEntity.status(HttpStatus.ok || HttpStatus.BAD_REQUEST)
	
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create(@RequestBody ArticleDTO articleDTO) { // @RequestBody: 넘어오는 정보는 json 문자열
		Article created = articleService.create(articleDTO);
		
		ResponseEntity<Article> result = 
		( created != null ) 
			? ResponseEntity.status(HttpStatus.OK).body(created)  			// 200(ok), 201(create)
			: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(created);	// 400 (error)
				
		return result;
	}
}