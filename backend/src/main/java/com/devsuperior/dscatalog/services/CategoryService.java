package com.devsuperior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRespository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRespository categoryRespository;
	
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return categoryRespository.findAll();
	}

}
