package com.project.hotel.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "beds")
	private Integer beds;

	@Column(name="price")
	private Double price;

	@Column(name = "bathroom")
	private Boolean bathroom;

	@Column(name = "tv")
	private Boolean tv;

	public Room(Integer beds, Double price, Boolean bathroom, Boolean tv) {
		this.beds = beds;
		this.price = price;
		this.bathroom = bathroom;
		this.tv = tv;
	}

	public Integer getPeople() {
		return beds;
	}
}
