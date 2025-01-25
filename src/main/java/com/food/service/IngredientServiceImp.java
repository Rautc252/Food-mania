package com.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.IngredientCategory;
import com.food.model.IngredientsItems;
import com.food.model.Restaurant;
import com.food.repository.IngredientCategoryRepository;
import com.food.repository.IngredientitemRepository;

@Service
public class IngredientServiceImp implements IngredientsService {
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	
	@Autowired
	private IngredientitemRepository ingredientitemRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	

	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional< IngredientCategory> opt = ingredientCategoryRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("ingredient category not found  ");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItems createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {

		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category=findIngredientCategoryById(categoryId);
		
		IngredientsItems item = new IngredientsItems();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientsItems ingredient = ingredientitemRepository.save(item);
		category.getIngredientsItems().add(ingredient);
		return ingredient;
	}

	@Override
	public List<IngredientsItems> findRestaurantsIngredients(Long restaurantId) {
		// TODO Auto-generated method stub
		return ingredientitemRepository.findRestaurantId(restaurantId);
	}

	@Override
	public IngredientsItems updateStock(Long id) throws Exception {
		Optional<IngredientsItems>optionalIngredientsItem=ingredientitemRepository.findById(id);
		if(optionalIngredientsItem.isEmpty()) {
			throw new Exception("ingredient not found");
		}
		IngredientsItems ingredientsItems=optionalIngredientsItem.get();
		ingredientsItems.setInStock(!ingredientsItems.isInStock());
		return ingredientitemRepository.save(ingredientsItems);
	}

}
