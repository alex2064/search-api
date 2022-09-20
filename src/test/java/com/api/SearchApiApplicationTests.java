package com.api;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.blog.controller.BlogController;
import com.api.blog.domain.entity.Word;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SearchApiApplicationTests {

	@Autowired
	private BlogController blogController;
	
	@Test
	void contextLoads() {

		
		//log.info(blogController.select("코딩", "accuracy", 1, 10));
		//blogController.select("", "accuracy", 1, 10);
		//blogController.select("코딩", "", 1, 10);
		//blogController.select("코딩", "accuracy", 0, 0);
		//blogController.select("코딩", "accuracy", 51, 51);
		//blogController.select("", "", 0, 0);
		
		for(int i=0; i<30; i++) {
			blogController.select("코딩", "accuracy", 1, 1);
			if(i>1) blogController.select("테스트", "accuracy", 1, 1);
			if(i>2) blogController.select("과제", "accuracy", 1, 1);
			if(i>3) blogController.select("API", "accuracy", 1, 1);
			if(i>4) blogController.select("케이스", "accuracy", 1, 1);
			if(i>5) blogController.select("exception", "accuracy", 1, 1);
			if(i>6) blogController.select("제출", "accuracy", 1, 1);
			if(i>7) blogController.select("문서", "accuracy", 1, 1);
			if(i>8) blogController.select("블로그", "accuracy", 1, 1);
			if(i>9) blogController.select("개발", "accuracy", 1, 1);
			
		}
		List<Word> result = blogController.selectWordRank();result.stream().forEach(word -> log.info(word.getWord() + "/" + word.getCnt()));
		
		
		
	}

}
