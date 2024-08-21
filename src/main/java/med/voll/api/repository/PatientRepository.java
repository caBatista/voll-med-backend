package med.voll.api.repository;

import med.voll.api.dto.PatientResponseDTO;
import med.voll.api.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Page<PatientResponseDTO> findAllByActiveTrue(Pageable pageable);
	
	Optional<Patient> findByIdAndActiveTrue(Long id);
}
