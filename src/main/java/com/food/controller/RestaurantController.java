package com.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.dto.RestaurantDto;
import com.food.model.Restaurant;
import com.food.model.User;
import com.food.request.CreateReastaurantRequest;
import com.food.service.RestaurantService;
import com.food.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {


	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurant(
		@RequestParam String keyword,
		@RequestHeader("Authorization")String jwt
	) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant=restaurantService.searchRestaurant(keyword);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Restaurant>> getAllRestaurant(
		
		@RequestHeader("Authorization")String jwt
	) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant=restaurantService.getAllRestaurant();
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(
		@PathVariable Long id,
		@RequestHeader("Authorization")String jwt
	) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant=restaurantService.findRestaurantById(id);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}/add-favourites")
	public ResponseEntity<RestaurantDto> addToFavourites(
		@PathVariable Long id,
		@RequestHeader("Authorization")String jwt
	) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		RestaurantDto restaurant=restaurantService.addToFavorites(id, user);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
}
