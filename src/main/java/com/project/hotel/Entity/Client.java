package com.project.hotel.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name="phone")
	private String phone;

	@Column(name = "mail")
	private String mail;

	@JsonIgnore
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private List<Booking> bookings;

	public Client(String clientName, String clientEmail, String clientPhone) {
		this.name = clientName;
		this.mail = clientEmail;
		this.phone = clientPhone;
	}
}
