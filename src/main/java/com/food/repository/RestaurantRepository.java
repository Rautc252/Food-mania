package com.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.food.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
//	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%',:query,'%'))"+
//	"OR lower(r.cuisionType) LIKE lower(concat('%',:query,'%'))")
//	List<Restaurant> findBySearchQuery(String query);
	

	@Query(value = "SELECT * FROM restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%',:query,'%'))" +
            " OR LOWER(r.cuision_type) LIKE LOWER(CONCAT('%',:query,'%'))", nativeQuery = true)
List<Restaurant> findBySearchQuery(String query);
	Restaurant findByOwnerId(Long userId);

	
}
