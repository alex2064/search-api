package com.api.blog.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@Getter
@RedisHash(value = "word")
public class Word{
	
	@Id
	private String word;
	
	@Setter
	private int cnt;
	
	public Word(String word, int cnt) {
		this.word = word;
		this.cnt = cnt;
	}
	
}
