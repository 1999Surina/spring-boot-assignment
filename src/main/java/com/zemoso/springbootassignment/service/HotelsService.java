package com.zemoso.springbootassignment.service;

import java.util.List;

import com.zemoso.springbootassignment.dto.HotelsDto;
import com.zemoso.springbootassignment.entity.Hotels;
import com.zemoso.springbootassignment.entity.Review;

public interface HotelsService {
	
	public List<Hotels> findAll();
	
	public Hotels findById(int theId);
	
	public void save(Hotels theHotels);
	
	public void deleteById(int theId);

	public List<Hotels> searchBy(String theName);

	List<Hotels> findAllByOrderByRatingDesc();
	
	public String getUsername();
	
	public List<Review> findAllReviews(int theId);
	
	public void addReview(Review theReview);
	
	public String getHotelsName(int theId);

	public HotelsDto findDtoById(int theId);
}
