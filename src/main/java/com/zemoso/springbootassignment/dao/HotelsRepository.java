package com.zemoso.springbootassignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zemoso.springbootassignment.entity.Hotels;

public interface HotelsRepository extends JpaRepository<Hotels, Integer> {

     List<Hotels> findByNameContainsOrCityContainsIgnoreCase(String theName, String City);
}
