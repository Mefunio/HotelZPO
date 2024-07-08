package com.project.hotel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "check_in")
	private LocalDate checkIn;

	@Column(name = "check_out")
	private LocalDate checkOut;

	@Column(name = "number_of_guests")
	private Integer numberOfGuests;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@JsonIgnoreProperties("bookings")
	@ManyToMany
	@JoinTable(
			name = "bookings_rooms",
			joinColumns = @JoinColumn(name = "booking_id"),
			inverseJoinColumns = @JoinColumn(name = "room_id")
	)
	@JsonManagedReference
	private List<Room> rooms;

	public void setRoom(Room room) {
		this.rooms = List.of(room);
	}
}
