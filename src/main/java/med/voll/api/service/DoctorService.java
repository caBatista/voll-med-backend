package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorCrRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.dto.DoctorUpRequestDTO;
import med.voll.api.exception.ObjectDeleteException;
import med.voll.api.exception.ObjectUpdateException;
import med.voll.api.model.Address;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
	private final DoctorRepository doctorRepository;
	
	public DoctorResponseDTO createDoctor(DoctorCrRequestDTO doctorDTO) {
		Address addressToSave = Address.builder()
				.street(doctorDTO.address().street())
				.zipCode(doctorDTO.address().zipCode())
				.number(doctorDTO.address().number())
				.complement(doctorDTO.address().complement())
				.city(doctorDTO.address().city())
				.state(doctorDTO.address().state())
				.build();
		
		Doctor doctorToSave = Doctor.builder()
				.name(doctorDTO.name())
				.email(doctorDTO.email())
				.phoneNumber(doctorDTO.phoneNumber())
				.crm(doctorDTO.crm())
				.specialty(doctorDTO.specialty())
				.address(addressToSave)
				.active(true)
				.build();
		
		var savedDoctor = doctorRepository.save(doctorToSave);
		
		return new DoctorResponseDTO(savedDoctor);
	}
	
	public Page<DoctorResponseDTO> findAll(Pageable pageable){
		var doctorsPage = doctorRepository.findAllByActiveTrue(pageable);
	
		return doctorsPage.map(DoctorResponseDTO::new);
	}
	
	public DoctorResponseDTO findById(Long id){
		var doctorOptional = doctorRepository.findByIdAndActiveTrue(id);
		
		return doctorOptional.map(DoctorResponseDTO::new).orElse(null);
	}
	
	public DoctorResponseDTO updateDoctor(DoctorUpRequestDTO doctorDTO) {
		var doctor = doctorRepository.findById(doctorDTO.id())
				.orElseThrow(() -> new ObjectUpdateException("Doctor not found"));
		
		doctor.update(doctorDTO);
		
		return new DoctorResponseDTO(doctor);
	}
	
	public void deleteDoctor(Long id) {
		var doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new ObjectDeleteException("Doctor not found"));
		
		doctor.delete();
	}
}
