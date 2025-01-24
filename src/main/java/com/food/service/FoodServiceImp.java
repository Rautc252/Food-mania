package com.food.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Category;
import com.food.model.Food;
import com.food.model.Restaurant;
import com.food.repository.FoodRepository;
import com.food.request.createFoodRequest;

@Service
public class FoodServiceImp implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public Food createFood(createFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCatergory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasional());
		food.setVegetarian(req.isVegeterian());
		
		Food savedFood = foodRepository.save(food);
		restaurant.getFoods().add(savedFood);
		
		return null;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		
		Food food=findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
		
		
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId,
											boolean isVegeterian, 
											boolean isNonveg, 
											boolean isSeasonal,String foodCategory) {
		List<Food> foods= foodRepository.findByRestaurantId(restaurantId);
		
		if(isVegeterian) {
			foods=filterByVegeterian(foods,isVegeterian);
		}
		if(isSeasonal) {
			foods=filterBySeasonal(foods,isSeasonal);
		}
		if(foodCategory!=null&& !foodCategory.equals("")) {
			foods=filterByCategory(foods,foodCategory);
		}
		if(isNonveg) {
			foods=filterByNonveg(foods,isNonveg);
		}
		return null;
	}

	
	private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
		
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
	}

	
	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		
		return foods.stream().filter(food -> {
			if(food.getFoodCatergory()!=null) {
				return food.getFoodCatergory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	
	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}

	
	private List<Food> filterByVegeterian(List<Food> foods, boolean isVegeterian) {
		
		return foods.stream().filter(food -> food.isVegetarian()==isVegeterian).collect(Collectors.toList());
	}

	
	@Override
	public List<Food> searchFood(String keyword) {
		
		return foodRepository.searchFood(keyword)
				;
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood=foodRepository.findById(foodId);
		if(optionalFood.isEmpty()) {
			throw new Exception("food not Exist...");
		}
		return optionalFood.get();
	}

	@Override
	public Food updateAvailibilityStatus(Long foodId) throws Exception {
		Food food=findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
