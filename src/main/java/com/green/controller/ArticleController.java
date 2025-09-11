package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import com.green.dto.Article;
import com.green.dto.ArticleDTO;
import com.green.repository.ArticleRepository;

@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository; 			//mapper 와 같은 역할
	
	@GetMapping("/WriteForm")
	public String wrtieForm() {
		return "article/write";
	}
	
	@PostMapping("/Write")
	public String write(ArticleDTO articleDTO) {
		//System.out.println("articleDTO: " + articleDTO);
		log.info("입력:{}", articleDTO);
		
		// h2 db에 Article table에 저장
		
		//toEntity() : articleDTO -> article 타입 변환 
		Article article = articleDTO.toEntity();
		
		// .save(article):: .save(entity type class 필요)
		// saved : 저장된 데이터(record)의 정보 다 가지고 있다.
		Article saved = articleRepository.save(article); 
		System.out.println(saved.getId());	// 저장된 글번호를 조회
		System.out.println(saved); 			// 저장된 전체 정보 조회
		
		return "redirect:/article/List";	// 목록으로 이동
	}
	
	@GetMapping("/List")
	public String List(Model model) {
		
		long totCount = articleRepository.count(); //SELECT COUNT(*) FROM ARTICLE
		System.out.println("전체자료수: "+ totCount);
		
		// DB조회
		// 타입으로 인해 오류가 발생-> 형변환을 해줘야한다.
		// List<Article> articleEntityList = articleRepository.findAll();
		
		// 해결책1
		//List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
		//System.out.println(articleEntityList);
		
		// 해결책2
		//Iterable<Article> articleEntityList = articleRepository.findAll();
		
		// 해결책3: 사용할 방법
		// articleRepository 에 있는 findAll()을 재정의한다.
		List<Article> articleList = articleRepository.findAll();
		System.out.println(articleList);
		
		model.addAttribute("articleList", articleList);
		
		return "article/list";
	}
	
	// PathVariable -> 무조건 get 방식 
	@GetMapping("/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		
		// 조회방법 1
		// Optional<Article> : null 이 들어와도 null point exception 이 발생하지 않도록 Optional 로 반환받는다
		// Optional<Article> article = articleRepository.findById(id);
		// DB에 ROW가 있으면 : Optional.of(article)
		// DB에 ROW가 없으면 : null 이 아니라 -> Optional.empty()
		
		// 조회방법 2: 값이 없을 때 null이 return 되도록 변경가능
		Article article = articleRepository.findById(id).orElse(null);
		
		model.addAttribute("article", article);
		
		return "article/view";
	}
	
	@GetMapping("{id}/Delete")
	public String delete(@PathVariable("id") Long id, RedirectAttributes rttr) {
		
		// 기존 방식
		//Article article = new Article(id, null, null);
		//articleRepository.delete(article);
	
		log.info(id + "번의 삭제요청이 들어왔습니다");	
		
		// 1. 삭제 대상을 조회한다.
		// 조회한 자료가 있으면 target 저장, 없으면 target <- null
		Article target = articleRepository.findById(id).orElse(null);
		
		// 2. 자료가 있으면 삭제 (웹의 특성상 없을수도 있다.)
		if(target != null) {
			articleRepository.delete(target);
			// 메세지를 만든다
			rttr.addFlashAttribute("msg", id+"번이 삭제되었습니다");
		}	
		return "redirect:/article/List";
	}
	
	@GetMapping("/{id:\\d+}/UpdateForm")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Article article = articleRepository.findById(id).orElse(null);
		model.addAttribute("article", article);
		
		return "article/update";
	}
	
	@PostMapping("/Update")
	public String update(@RequestParam HashMap<String, Object> map) {
		
		// 넘어온 값 처리
		Long id = Long.parseLong(String.valueOf(map.get("id")));
		String title = String.valueOf(map.get("title"));
		String content = String.valueOf(map.get("content"));
		
		Article article = new Article(id, title, content);
		articleRepository.save(article);
		
		return "redirect:/article/List";
	}
		
}