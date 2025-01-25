package com.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.model.IngredientsItems;

public interface IngredientitemRepository extends JpaRepository<IngredientsItems,Long >{
	
	List<IngredientsItems>findRestaurantId(Long id);
		
	
	
}
