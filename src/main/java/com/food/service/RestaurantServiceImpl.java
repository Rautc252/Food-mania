package com.food.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.food.dto.RestaurantDto;
import com.food.model.Address;
import com.food.model.Restaurant;
import com.food.model.User;
import com.food.repository.AddressRepository;
import com.food.repository.RestaurantRepository;
import com.food.repository.UserRepository;
import com.food.request.CreateReastaurantRequest;



@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Restaurant createRestaurant(CreateReastaurantRequest req, User user) {
		
		Address address = addressRepository.save(req.getAddress());
		
		Restaurant restaurant=new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());;
		restaurant.setCuisionType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOperningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateReastaurantRequest updatedRestaurant) throws Exception {
		
		Restaurant restaurant=findRestaurantById(restaurantId);
		
		if(restaurant.getCuisionType()!=null) {
			restaurant.setCuisionType(updatedRestaurant.getCuisineType());
		}
		if(restaurant.getDescription()!=null){
			restaurant.setDescription(updatedRestaurant.getDescription());
			
		}
		if(restaurant.getName()!=null) {
			restaurant.setName(updatedRestaurant.getName());
		}
		
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		
		Restaurant restaurant=findRestaurantById(restaurantId);
		
		restaurantRepository.delete(restaurant);
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> opt = restaurantRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("restaurant not found with id "+id);
		}
		
		return opt.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
		if(restaurant==null) {
			throw new Exception("restaurant not found with user id"+userId);
			
		}
		
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
	    Restaurant restaurant = findRestaurantById(restaurantId);

	    RestaurantDto dto = new RestaurantDto();
	    dto.setDescription(restaurant.getDescription());
	    dto.setImage(restaurant.getImages());
	    dto.setTitle(restaurant.getName());
	    dto.setId(restaurantId);

	    boolean isFavorite = false;
	    List<RestaurantDto>favorites=user.getFavorites();
	    // Iterate through the favorites list to check if the restaurant already exists
	    for (RestaurantDto favorite : favorites) {
	        if (favorite.getId().equals(restaurantId)) {
	            isFavorite = true;
	            break;
	        }
	    }

	    // Add or remove the restaurant from the favorites list based on the check
	    if (isFavorite) {
	        // Remove the existing restaurant from favorites
	        user.getFavorites().removeIf(favorite -> favorite.getId().equals(dto.getId()));
	    } else {
	        // Add the new restaurant to favorites
	        user.getFavorites().add(dto);
	    }

	    // Save the updated user entity
	    userRepository.save(user);

	    return dto;
	}


	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		// TODO Auto-generated method stub
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception{
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}

}
