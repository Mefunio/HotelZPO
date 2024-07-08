package com.project.hotel.Controller;

import com.project.hotel.Entity.Booking;
import com.project.hotel.Entity.Client;
import com.project.hotel.Entity.Room;
import com.project.hotel.Service.BookingService;
import com.project.hotel.Service.ClientService;
import com.project.hotel.Service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService bookingService;
	private final ClientService clientService;
	private final RoomService roomService;

	public BookingController(BookingService bookingService, ClientService clientService, RoomService roomService) {
		this.bookingService = bookingService;
		this.clientService = clientService;
		this.roomService = roomService;
	}

	@GetMapping
	public String getBookings(Model model) {
		model.addAttribute("bookings", bookingService.findAll());
		return "booking";
	}

	@GetMapping("/findRooms")
	public String showFindRoomsForm(Model model) {
		model.addAttribute("bookingInfo", new Booking());
		return "findRooms";
	}

	@PostMapping("/findRooms")
	public String findRooms(@RequestParam LocalDate checkIn,
	                        @RequestParam LocalDate checkOut,
	                        @RequestParam Integer people,
	                        @RequestParam(defaultValue = "false") Boolean bathroom,
	                        @RequestParam(defaultValue = "false") Boolean tv,
	                        Model model) {

		List<Room> rooms = roomService.findAvailableRooms(checkIn, checkOut, people, bathroom, tv);

		if (rooms.isEmpty()) {
			model.addAttribute("message", "No rooms available for the selected dates");
			return "findRooms";
		} else {
			model.addAttribute("rooms", rooms);
		}

		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("people", people);
		model.addAttribute("bathroom", bathroom);
		model.addAttribute("tv", tv);
		model.addAttribute("rooms", rooms);

		return "addBooking";
	}

	@PostMapping("/add")
	public String addBooking(@ModelAttribute Booking booking,
	                         @RequestParam(required = false) Long roomId,
	                         @RequestParam String clientName,
	                         @RequestParam String clientEmail,
	                         @RequestParam String clientPhone,
	                         @RequestParam Integer people) {

		Client client = new Client(clientName, clientEmail, clientPhone);
		clientService.saveClient(client);

		booking.setClient(client);
		booking.setNumberOfGuests(people);

		Room room = roomService.findRoomById(roomId);
		booking.setRoom(room);

		bookingService.addBooking(booking);

		return "redirect:/bookings";
	}

	@PostMapping("/delete/{id}")
	public String deleteBooking(@PathVariable Long id) {
		bookingService.deleteBookingById(id);
		return "redirect:/bookings";
	}
}