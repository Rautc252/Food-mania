package com.food.service;

import java.util.List;

import com.food.dto.RestaurantDto;
import com.food.model.Restaurant;
import com.food.model.User;
import com.food.request.CreateReastaurantRequest;

public interface RestaurantService {
	
	public Restaurant createRestaurant(CreateReastaurantRequest req, User user) ;
		
	
	public Restaurant updateRestaurant(Long restaurantId , CreateReastaurantRequest updatedRestaurant)throws Exception;

	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	
	
	public Restaurant findRestaurantById(Long id)throws Exception;
	
	
	public Restaurant getRestaurantByUserId(Long userId) throws Exception;
	
	
	public RestaurantDto  addToFavorites(Long restaurantId,User user) throws Exception;
	
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
}
