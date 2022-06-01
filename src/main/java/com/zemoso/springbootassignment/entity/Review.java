package com.zemoso.springbootassignment.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="hotels_id")
	private int hotels_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="feedback")
	private String feedback;

	public Review(int id, int hotels_id, String username) {
		this.id = id;
		this.hotels_id = hotels_id;
		this.username = username;

	}
}
