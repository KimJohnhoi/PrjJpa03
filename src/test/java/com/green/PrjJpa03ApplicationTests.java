package com.green;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.repository.ArticleRepository;

@SpringBootTest
class PrjJpa03ApplicationTests {

	@Autowired
	ArticleRepository articleRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void test1() {
		double n1 = 10;
		double n2 = 3;
		double n = n1/n2;
		
		System.out.println( n );
		
		//assertEquals(n, 3.5);
		assertEquals(n, 3.3, 0.1);  // 오차 범위
	}
	
	@Test
	void test2() {
		long totCnt = articleRepository.count();
		System.out.println(totCnt);
		
		assertEquals(6, totCnt);
	}
}
