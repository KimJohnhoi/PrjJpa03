package com.green.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ArticleDTO {
	// Field : formtag 의 name 속성(반드시 소문자로 시작할것)과 DTO field명이 같아야 맵핑됨 
	private String title;
	private String content;
	
	public Article toEntity() {
		Article article = new Article(null, this.title, this.content);
		
		return article;
	}
}