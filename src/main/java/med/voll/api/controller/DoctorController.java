package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorCrRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.dto.DoctorUpRequestDTO;
import med.voll.api.model.Doctor;
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
public class DoctorController {
	private final DoctorService doctorService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody @Valid DoctorCrRequestDTO doctorDTO, UriComponentsBuilder uriBuilder) {
		var doctor = doctorService.createDoctor(doctorDTO);
		
		var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
		var dto = new DoctorResponseDTO(doctor);
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<DoctorResponseDTO>> findAll(@PageableDefault(size=10) Pageable pageable){
		var page = doctorService.findAll(pageable);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id){
		var dto = doctorService.findById(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestBody @Valid DoctorUpRequestDTO doctorDTO) {
		var doctor = doctorService.updateDoctor(doctorDTO);
		var dto = new DoctorResponseDTO(doctor);
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
		
		return ResponseEntity.noContent().build();
	}
}
