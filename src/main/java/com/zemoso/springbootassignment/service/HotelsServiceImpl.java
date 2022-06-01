package com.zemoso.springbootassignment.service;

import java.util.List;
import java.util.Optional;


import com.zemoso.springbootassignment.dto.HotelsDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zemoso.springbootassignment.dao.HotelsRepository;
import com.zemoso.springbootassignment.entity.Hotels;
import com.zemoso.springbootassignment.entity.Review;

@Service
public class HotelsServiceImpl implements HotelsService {
    Logger logger = LoggerFactory.getLogger(HotelsServiceImpl.class);

    private HotelsRepository hotelsRepository;

    @Autowired
    public HotelsServiceImpl(HotelsRepository theHotelsRepository) {
        hotelsRepository = theHotelsRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Hotels> findAll() throws RuntimeException {
        return hotelsRepository.findAll();
    }

    @Override
    public List<Hotels> findAllByOrderByRatingDesc()  throws RuntimeException{
        //return hotelsRepository.findAllByOrderByRatingDesc();
        return hotelsRepository.findAll(Sort.by(Sort.Direction.DESC,"rating"));
    }


    @Override
    public Hotels findById(int theId)  throws RuntimeException{
        Optional<Hotels> result = hotelsRepository.findById(theId);
        Hotels theHotels;
        if (result.isPresent())
            theHotels = result.get();
        else {
            throw new RuntimeException("Hotel not found - " + theId);
        }
        return theHotels;
    }

    @Override
    public HotelsDto findDtoById(int theId) throws RuntimeException{
        Optional<Hotels> result = hotelsRepository.findById(theId);
        Hotels theHotels;
        if (result.isPresent())
            theHotels = result.get();
        else {
            throw new RuntimeException("Hotel not found - " + theId);
        }
        HotelsDto hotelsDto = this.modelMapper.map(result, HotelsDto.class);
        logger.info(String.valueOf(hotelsDto));
        return hotelsDto;
    }


    @Override
    public void save(Hotels theHotels)  throws RuntimeException {

        hotelsRepository.save(theHotels);
    }

    @Override
    public void deleteById(int theId) throws RuntimeException {
        hotelsRepository.deleteById(theId);
    }

    @Override
    public List<Hotels> searchBy(String theName)  throws RuntimeException{

        List<Hotels> results;

        if (theName != null && (theName.trim().length() > 0)) {
            results = hotelsRepository.findByNameContainsOrCityContainsIgnoreCase(theName, theName);
        } else {
            results = findAll();
        }

        return results;
    }

    @Override
    public String getUsername()  throws RuntimeException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }

    @Override
    public List<Review> findAllReviews(int theId)  throws RuntimeException{
        Hotels theHotels = findById(theId);
        List<Review> reviews = theHotels.getReviews();
        return reviews;
    }

    @Override
    public void addReview(Review theReview)  throws RuntimeException{
        if(theReview.getFeedback()!=""){
            int theId = theReview.getHotels_id();

            Hotels theHotels = findById(theId);
            theHotels.addReview(theReview);
            save(theHotels);
        }

    }

    @Override
    public String getHotelsName(int theId)  throws RuntimeException{
        Hotels theHotels = findById(theId);
        String name = theHotels.getName();
        return name;
    }
}





