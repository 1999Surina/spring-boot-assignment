package com.zemoso.springbootassignment.controller;

import java.util.List;

import javax.validation.Valid;

import com.zemoso.springbootassignment.dto.HotelsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zemoso.springbootassignment.entity.Hotels;
import com.zemoso.springbootassignment.entity.Review;
import com.zemoso.springbootassignment.service.HotelsService;

@Controller
@RequestMapping("/hotels")
public class HotelsController {

	@Autowired
	private HotelsService hotelsService;
	
	
	public HotelsController(HotelsService theHotelsService) {
		hotelsService = theHotelsService;
	}


	@GetMapping("/list")
	public String listHotels(@RequestParam(required = false) String orderBy,Model theModel) {
		
		List<Hotels> theHotels=null;
		if(orderBy==null) {
			theHotels = hotelsService.findAll();
		}
		else if(orderBy.equals("rating")){
			theHotels = hotelsService.findAllByOrderByRatingDesc();
		}
		theModel.addAttribute("hotels", theHotels);
		
		return "hotels/list-hotels";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Hotels theHotels = new Hotels();
		
		theModel.addAttribute("hotels", theHotels);
		
		return "hotels/hotels-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("hotelsId") int theId,
									Model theModel) {

		Hotels theHotels = hotelsService.findById(theId);

		theModel.addAttribute("hotels", theHotels);
		
		return "hotels/hotels-form";
	}

	@GetMapping("/checkDto")
	public String showFormForUpdateDto(@RequestParam("hotelsId") int theId,
									Model theModel) {

		HotelsDto theHotels = hotelsService.findDtoById(theId);

		theModel.addAttribute("hotels", theHotels);

		return "hotels/hotels-form";
	}
	
	
	@PostMapping("/save")
	public String saveHotels(
			@Valid @ModelAttribute("hotels") Hotels theHotels,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "hotels/hotels-form";
		}
		hotelsService.save(theHotels);

		return "redirect:/hotels/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("hotelsId") int theId) {
		
		hotelsService.deleteById(theId);

		return "redirect:/hotels/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("nameOrCity") String theName,
						 Model theModel) {
		
		List<Hotels> theHotels = hotelsService.searchBy(theName);
		theModel.addAttribute("hotels", theHotels);
		return "/hotels/list-hotels";
		
	}
	
	@GetMapping("/reviews")
	public String reviews(@RequestParam("id") int theId,
							Model theModel) {
		String username = hotelsService.getUsername();
		Review theReview = new Review(0,theId,username);
		List<Review> reviews = hotelsService.findAllReviews(theId);
		theModel.addAttribute("name",
				hotelsService.getHotelsName(theId));
		theModel.addAttribute("review", theReview);
		theModel.addAttribute("reviews", reviews);
		return "/hotels/list-reviews";		
	}	
	
	@PostMapping("/addReview")
	public String addReview(@ModelAttribute("review") Review theReview) {
		hotelsService.addReview(theReview);
		return "redirect:/hotels/reviews?id="+theReview.getHotels_id();
	}
}