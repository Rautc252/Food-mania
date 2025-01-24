package com.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.model.Cart;

public interface Cartrepository extends JpaRepository<Cart, Long>{

}
