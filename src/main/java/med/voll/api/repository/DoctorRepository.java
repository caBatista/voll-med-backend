package med.voll.api.repository;

import med.voll.api.model.Doctor;
import med.voll.api.type.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Page<Doctor> findAllByActiveTrue(Pageable pageable);
	Optional<Doctor> findByIdAndActiveTrue(Long id);
	
	@Query("""
			SELECT d
			FROM Doctor d
			WHERE d.active = true
			AND d.specialty = :specialty
			AND d.id NOT IN (
								SELECT a.doctor.id
								FROM Appointment a
								WHERE a.date
								BETWEEN :start
								AND :end
							)
			""")
	List<Doctor> findAvailableDoctorsBySpecialty(LocalDateTime start, LocalDateTime end, Specialty specialty);
	
}
