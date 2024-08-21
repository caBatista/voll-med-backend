package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.PatientCrRequestDTO;
import med.voll.api.dto.PatientResponseDTO;
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
public class PatientController {
	
	private final PatientService patientService;
	
	@PostMapping
	public ResponseEntity createPatient(@RequestBody @Valid PatientCrRequestDTO patientDTO, UriComponentsBuilder uriBuilder) {
		var patient = patientService.createPatient(patientDTO);
		
		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
		var dto = new PatientResponseDTO(patient);
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<PatientResponseDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
		var patients = patientService.findAll(pageable);
		
		if(patients.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(patients);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PatientResponseDTO> findById(@PathVariable Long id){
		var patient = patientService.findById(id);
		
		if(patient == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(patient);
	}
}
