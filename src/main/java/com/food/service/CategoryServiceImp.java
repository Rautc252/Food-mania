package com.food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.food.model.Category;

@Service
public class CategoryServiceImp  implements CategoryService{

	@Override
	public Category createCategory(String name, Long userId) {
		
		return null;
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
