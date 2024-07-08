package com.project.hotel.Service;

import com.project.hotel.Entity.Booking;
import com.project.hotel.Entity.Client;
import com.project.hotel.Repository.BookingRepository;
import com.project.hotel.Repository.ClientRepository;
import com.project.hotel.Repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final RoomRepository roomRepository;
	private final ClientRepository clientRepository;

	public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository,
	                      ClientRepository clientRepository) {
		this.bookingRepository = bookingRepository;
		this.roomRepository = roomRepository;
		this.clientRepository = clientRepository;
	}

	public void deleteBookingById(Long id) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null) {
			Client client = booking.getClient();
			bookingRepository.deleteById(id);
			if (bookingRepository.findByClientId(client.getId()).isEmpty()) {
				clientRepository.deleteById(client.getId());
			}
		}
	}

	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	public void addBooking(Booking booking) {
		bookingRepository.save(booking);
	}
}
