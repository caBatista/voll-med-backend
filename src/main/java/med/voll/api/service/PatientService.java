package med.voll.api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.PatientCrRequestDTO;
import med.voll.api.model.Address;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
	
	private final PatientRepository patientRepository;
	
	public Patient createPatient(@Valid PatientCrRequestDTO patientDTO) {
		Address addressToSave = Address.builder()
				.street(patientDTO.address().street())
				.zipCode(patientDTO.address().zipCode())
				.number(patientDTO.address().number())
				.complement(patientDTO.address().complement())
				.city(patientDTO.address().city())
				.state(patientDTO.address().state())
				.build();
		
		Patient patientToSave = Patient.builder()
				.name(patientDTO.name())
				.email(patientDTO.email())
				.phoneNumber(patientDTO.phoneNumber())
				.cpf(patientDTO.cpf())
				.address(addressToSave)
				.build();
		
		return patientRepository.save(patientToSave);
		
	}
}
