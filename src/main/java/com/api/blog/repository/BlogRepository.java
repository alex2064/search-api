package com.api.blog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.api.blog.domain.entity.Word;

public interface BlogRepository extends CrudRepository<Word, String>{

	public List<Word> findTop10ByOrderByCntDesc();
	
}
