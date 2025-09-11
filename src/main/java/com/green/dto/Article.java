package com.green.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// 설정된 애노테이션을 통해 실제 DB에 TABLE 을 생성해준다.
// 주의: import는 jakarta로 해준다.
@Entity 						// DB TABLE 에 대응하는 클래스 : 애노테이션
public class Article {
	@Id 						// primary key 설정
	@GeneratedValue				// sequence 설정: 번호 자동증가
	private Long id;			// Long: null 입력가능 (int: null 입력불가! 주의 // Integer 가능 // 두개 구분할것!)
	
	@Column						// DB column 설정
	private String title;
	
	@Column
	private String content;

	// 기본 생성자: @NoArgsConstructor
	public Article() {}
	// Constructor:  @AllArgsConstructor (모든 인자가 있는 생성자)
	public Article(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	// @ToString
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
		
	public Long getId() {
		return this.id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(Long id) {
		this.id = id;
	}
}