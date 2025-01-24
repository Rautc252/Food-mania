package com.food.request;

import java.util.List;

import com.food.model.Category;
import com.food.model.IngredientsItems;

import lombok.Data;

@Data
public class createFoodRequest {
		
		private String name;
		private String description;
		private Long price ;
		
		private Category category;
		
		private List<String>images;
		
		private Long restaurantId;
		private boolean vegeterian;
		private boolean seasional;
		private List<IngredientsItems> ingredients;
		
}
