package com.project.hotel.Service;

import com.project.hotel.Entity.Room;
import com.project.hotel.Repository.BookingRepository;
import com.project.hotel.Repository.ClientRepository;
import com.project.hotel.Repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final BookingRepository bookingRepository;
	private final ClientRepository clientRepository;

	public RoomService(RoomRepository roomRepository, BookingRepository bookingRepository,
	                   ClientRepository clientRepository) {
		this.roomRepository = roomRepository;
		this.bookingRepository = bookingRepository;
		this.clientRepository = clientRepository;
	}

	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	public Room findRoomById(Long id) {
		return roomRepository.findById(id).orElse(null);
	}

	public List<Room> findAvailableRooms(LocalDate checkIn, LocalDate checkOut, Integer people, Boolean bathroom,
	                                     Boolean tv) {

		return findAll().stream()
				.filter(room -> !bookingRepository.findBookingsByRoomAndDates(room.getId(), checkIn, checkOut)
						.stream()
						.anyMatch(booking -> booking.getRooms().contains(room)))
				.filter(room -> room.getPeople() >= people)
				.filter(room -> room.getBathroom() == bathroom)
				.filter(room -> room.getTv() == tv)
				.collect(Collectors.toList());
	}
}
