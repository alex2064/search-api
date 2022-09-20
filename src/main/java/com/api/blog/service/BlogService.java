package com.api.blog.service;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.blog.domain.entity.Word;
import com.api.blog.repository.BlogRepository;
import com.api.common.exception.InvalidInputException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BlogService {
	
	private final BlogRepository blogRepository;
	
	
	public String selectKakao(String query, String sort, int page, int size){
		
		StringBuilder message = new StringBuilder();
		if(query.trim().equals("")) {
			message.append(", query parameter required");
		}
		
		if(!sort.trim().equals("accuracy") && !sort.trim().equals("recency")) {
			message.append(", sort parameter one of accuracy and recency");
		}
		
		if(page < 1) {
			message.append(", page is less than min");
		}else if(page > 50) {
			message.append(", page is more than max");
		}
		
		if(size < 1) {
			message.append(", size is less than min");
		}else if(size > 50) {
			message.append(", size is more than max");
		}
		
		if(message.length() > 0) {
			message.delete(0, 2);
			throw new InvalidInputException(message.toString());
		}
		
		
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
