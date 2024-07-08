package com.project.hotel.Init;

import com.project.hotel.Entity.Room;
import com.project.hotel.Repository.BookingRepository;
import com.project.hotel.Repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

	private final RoomRepository roomRepository;
	private final BookingRepository bookingRepository;

	public DbInit(RoomRepository roomRepository, BookingRepository bookingRepository) {
		this.roomRepository = roomRepository;
		this.bookingRepository = bookingRepository;
	}

	@Override
	public void run(String... args) {
		if (roomRepository.count() == 0) {
			List<Room> rooms = Arrays.asList(
					new Room(4, 75.00, true, false),
					new Room(2, 50.00, false, false),
					new Room(6, 100.00, true, true),
					new Room(8, 150.00, true, true),
					new Room(1, 25.00, true, false),
					new Room(3, 60.00, false, false)
			);
			roomRepository.saveAll(rooms);
		} else {
			System.out.println("Rooms already exist in the database");
		}
	}
}
