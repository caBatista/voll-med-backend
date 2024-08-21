package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorCrRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.dto.DoctorUpRequestDTO;
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
	
	public Doctor createDoctor(DoctorCrRequestDTO doctorDTO) {
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
		
		return doctorRepository.save(doctorToSave);
	}
	
	public Page<DoctorResponseDTO> findAll(Pageable pageable){
		return doctorRepository.findAllByActiveTrue(pageable);
	}
	
	public DoctorResponseDTO findById(Long id){
		var doctor = doctorRepository.findByIdAndActiveTrue(id);
		
		return new DoctorResponseDTO(doctor);
	}
	
	public Doctor updateDoctor(DoctorUpRequestDTO doctorDTO) {
		var doctor = doctorRepository.findById(doctorDTO.id())
				.orElseThrow();
		
		doctor.update(doctorDTO);
		
		return doctor;
	}
	
	public void deleteDoctor(Long id) {
		var doctor = doctorRepository.findById(id)
				.orElseThrow();
		
		doctor.delete();
	}
}
