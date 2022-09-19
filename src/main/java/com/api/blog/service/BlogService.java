package com.api.blog.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.blog.domain.entity.Word;
import com.api.blog.repository.BlogRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BlogService {
	
	private final BlogRepository blogRepository;
	
	public String select(String query, String sort, int page, int size){
		
		WebClient webClient = WebClient
							.builder()
							.baseUrl("https://dapi.kakao.com")
							.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
							.build();

		Mono<String> response = webClient
							.get()
							.uri(builder -> builder.path("/v2/search/blog")
													.queryParam("query", query)
													.queryParam("sort", sort)
													.queryParam("page", page)
													.queryParam("size", size)
													.build())
							.header("Authorization", "KakaoAK ac5d9a371616d5c1a4c00e6e81230a6e")
							.retrieve()
							.bodyToMono(String.class);
		
		String result = response.block();
		
		
		Word word = blogRepository.findById(query.toLowerCase()).orElse(new Word(query.toLowerCase(), 0));
		word.setCnt(word.getCnt()+1);
		
		blogRepository.save(word);
		
		return result;
	}
	
	public List<Word> selectWordRank(){
		List<Word> list = blogRepository.findTop10ByOrderByCntDesc();
		return list;
	}
	
}
