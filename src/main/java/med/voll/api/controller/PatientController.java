package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.PatientCrRequestDTO;
import med.voll.api.dto.PatientResponseDTO;
import med.voll.api.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
	
	private final PatientService patientService;
	
	@PostMapping
	public ResponseEntity createPatient(@RequestBody @Valid PatientCrRequestDTO patientDTO, UriComponentsBuilder uriBuilder) {
		var patient = patientService.createPatient(patientDTO);
		
		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
		var dto = new PatientResponseDTO(patient);
		
		return ResponseEntity.created(uri).body(dto);
	}
}
