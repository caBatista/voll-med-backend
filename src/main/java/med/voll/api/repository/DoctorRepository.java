package med.voll.api.repository;

import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Page<DoctorResponseDTO> findAllByActiveTrue(Pageable pageable);
	DoctorResponseDTO findByIdAndActiveTrue(Long id);
}
