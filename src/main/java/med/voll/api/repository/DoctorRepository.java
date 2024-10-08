package med.voll.api.repository;

import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Page<Doctor> findAllByActiveTrue(Pageable pageable);
	Optional<Doctor> findByIdAndActiveTrue(Long id);
}
