package com.project.hotel.Service;

import com.project.hotel.Entity.Client;
import com.project.hotel.Repository.BookingRepository;
import com.project.hotel.Repository.ClientRepository;
import com.project.hotel.Repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final RoomRepository roomRepository;
	private final BookingRepository bookingRepository;

	public ClientService(ClientRepository clientRepository, RoomRepository roomRepository,
	                     BookingRepository bookingRepository) {
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
		this.bookingRepository = bookingRepository;
	}

	public void saveClient(Client client) {
		clientRepository.save(client);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client findClientById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}
}
