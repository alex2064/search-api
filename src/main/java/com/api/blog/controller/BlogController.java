package com.api.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.domain.entity.Word;
import com.api.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/api/blog")
@RestController
public class BlogController {
	
	private final BlogService blogService;
	
	
	@GetMapping("")
	public String select(@RequestParam String query
							, @RequestParam(required = false, defaultValue = "accuracy") String sort	// accuracy, recency
							, @RequestParam(required = false, defaultValue = "1") int page				// 1~50
							, @RequestParam(required = false, defaultValue = "10") int size){			// 1~50

		String result = blogService.selectKakao(query, sort, page, size);
		
		return result;
	}
	
	
	@GetMapping("/rank")
	public List<Word> selectWordRank(){

		List<Word> result = blogService.selectWordRank();
		
		return result;
	}
	
	
}
