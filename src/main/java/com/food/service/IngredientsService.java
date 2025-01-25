package com.food.service;

import java.util.List;

import com.food.model.IngredientCategory;
import com.food.model.IngredientsItems;

public interface IngredientsService {
	
	public IngredientCategory createIngredientCategory(String name,Long restaurantId)throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
	
	public List<IngredientCategory>findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItems createIngredientItem(Long restaurantId,String ingredientName,Long categoryId)throws Exception;
	
	public List<IngredientsItems> findRestaurantsIngredients(Long restaurantId);
	
	public IngredientsItems updateStock(Long id)throws Exception;
}
