package com.green.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.green.dto.Article;
import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>{

	@Override
	List<Article> findAll();
	// Iterable<> findAll() return -> List<Article> findAll()
	// 상속관계를 이용하여 List를 Iterable 로 UpCasting 하여 Casting 
}