package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorCrRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.dto.DoctorUpRequestDTO;
import med.voll.api.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
	private final DoctorService doctorService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody @Valid DoctorCrRequestDTO doctorDTO, UriComponentsBuilder uriBuilder) {
		var doctor = doctorService.createDoctor(doctorDTO);
		
		var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.id()).toUri();
		
		return ResponseEntity.created(uri).body(doctor);
	}
	
	@GetMapping
	public ResponseEntity<Page<DoctorResponseDTO>> findAll(@PageableDefault() Pageable pageable) {
		var page = doctorService.findAll(pageable);
		
		if (page.getTotalElements() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id){
		var doctor = doctorService.findById(id);
		
		if(doctor == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(doctor);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestBody @Valid DoctorUpRequestDTO doctorDTO) {
		var doctor = doctorService.updateDoctor(doctorDTO);
		
		return ResponseEntity.ok(doctor);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
		
		return ResponseEntity.noContent().build();
	}
}
