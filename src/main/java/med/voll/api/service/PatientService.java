package med.voll.api.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.PatientCrRequestDTO;
import med.voll.api.dto.PatientResponseDTO;
import med.voll.api.dto.PatientUpRequestDTO;
import med.voll.api.model.Address;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
	
	private final PatientRepository patientRepository;
	
	public PatientResponseDTO createPatient(@Valid PatientCrRequestDTO patientDTO) {
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
				.active(true)
				.build();
		
		var savedPatient = patientRepository.save(patientToSave);
		
		return new PatientResponseDTO(savedPatient);
	}
	
	public Page<PatientResponseDTO> findAll(Pageable pageable) {
		var patientsPage = patientRepository.findAllByActiveTrue(pageable);
		
		return patientsPage.map(PatientResponseDTO::new);
	}
	
	public PatientResponseDTO findById(Long id) {
		var patientOptional = patientRepository.findByIdAndActiveTrue(id);
		
		return patientOptional.isPresent() ? new PatientResponseDTO(patientOptional.get()) : null;
	}
	
	public PatientResponseDTO updatePatient(@Valid PatientUpRequestDTO patientDTO) {
		var patient = patientRepository.findById(patientDTO.id())
				.orElseThrow();
		
		patient.update(patientDTO);
		
		return new PatientResponseDTO(patient);
	}
	
	public void deletePatient(Long id) {
		var patient = patientRepository.findById(id)
				.orElseThrow();
		
		patient.delete();
	}
}
