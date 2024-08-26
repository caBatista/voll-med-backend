package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.PatientCrRequestDTO;
import med.voll.api.dto.PatientResponseDTO;
import med.voll.api.dto.PatientUpRequestDTO;
import med.voll.api.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PatientController {
	
	private final PatientService patientService;
	
	@PostMapping
	public ResponseEntity createPatient(@RequestBody @Valid PatientCrRequestDTO patientDTO, UriComponentsBuilder uriBuilder) {
		var patient = patientService.createPatient(patientDTO);
		
		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.id()).toUri();
		
		return ResponseEntity.created(uri).body(patient);
	}
	
	@GetMapping
	public ResponseEntity<Page<PatientResponseDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
		var page = patientService.findAll(pageable);
		
		if(page.getTotalElements() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PatientResponseDTO> findById(@PathVariable Long id){
		var patient = patientService.findById(id);
		
		if(patient == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(patient);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<PatientResponseDTO> updatePatient(@RequestBody @Valid PatientUpRequestDTO patientDTO) {
		var patient = patientService.updatePatient(patientDTO);
		
		return ResponseEntity.ok(patient);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		
		return ResponseEntity.noContent().build();
	}
}
