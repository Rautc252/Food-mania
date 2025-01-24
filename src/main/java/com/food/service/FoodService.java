package com.food.service;

import java.util.List;

import com.food.model.Category;
import com.food.model.Food;
import com.food.model.Restaurant;
import com.food.request.createFoodRequest;

public interface FoodService {
	
	public Food createFood (createFoodRequest req , Category category , Restaurant restaurant);
	
	
	void deleteFood(Long foodId)throws Exception;
	
	public  List<Food> getRestaurantsFood(Long restaurantId,boolean isVegeterian,boolean isNonveg,boolean isSeasonal,String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId)throws Exception;
	
	public Food updateAvailibilityStatus(Long foodId)throws Exception;
	
	
}
