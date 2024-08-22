package med.voll.api.repository;

import med.voll.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	@Query("""
			SELECT a
			FROM Appointment a
			WHERE a.patient.id = :id
			AND a.active = TRUE
			AND FUNCTION('DATE', a.date)  = FUNCTION('DATE', :appointmentDate)
			""")
	Optional<Appointment> findPatientAppointments(Long id, LocalDateTime appointmentDate);
	
	@Query("""
			SELECT a
			FROM Appointment a
			WHERE a.doctor.id = :id
			AND a.active = TRUE
			AND a.date > :start
			AND a.date < :end
			""")
	Optional<Appointment> findDoctorAppointments(Long id, Object start, Object end);
	
	Optional<Appointment> findByIdAndActiveTrue(Long id);
}
