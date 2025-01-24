package com.food.model;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private Long totalAmount;
	
	private String orderStatus;
	
	private Date createdAt;
	
	@ManyToOne
	private Address deliveryAddress;
	
	@OneToMany
	private List<Orderitem>items= new ArrayList<>();
	
	private int totalItem;
	
	private int totalprice;
}
