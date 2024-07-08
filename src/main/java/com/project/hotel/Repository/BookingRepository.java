package com.project.hotel.Repository;

import com.project.hotel.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByClientId(Long id);

	@Query("SELECT b FROM Booking b JOIN b.rooms r WHERE r.id = :roomId AND " +
			"(b.checkIn <= :endDate AND b.checkOut >= :startDate)")
	List<Booking> findBookingsByRoomAndDates(@Param("roomId") Long roomId,
	                                         @Param("startDate") LocalDate startDate,
	                                         @Param("endDate") LocalDate endDate);
}
