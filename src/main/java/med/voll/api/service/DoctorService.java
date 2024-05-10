package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.model.Address;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
	private final DoctorRepository doctorRepository;
	
	public Doctor createDoctor(DoctorRequestDTO doctorDTO) {
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
				.build();
		
		return doctorRepository.save(doctorToSave);
	}
	
	public List<DoctorResponseDTO> findAll(){
		var doctors = doctorRepository.findAll();
		
		return doctors.stream()
				.map(DoctorResponseDTO::new)
				.toList();
	}
}
