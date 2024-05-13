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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService doctorService;
	
	@PostMapping
	@Transactional
	public Doctor createDoctor(@RequestBody @Valid DoctorCrRequestDTO doctorDTO) {
		return doctorService.createDoctor(doctorDTO);
	}
	
	@GetMapping
	public Page<DoctorResponseDTO> findAll(@PageableDefault(size=10) Pageable pageable){
		return doctorService.findAll(pageable);
	}
	
	@PutMapping
	@Transactional
	public Doctor updateDoctor(@RequestBody @Valid DoctorUpRequestDTO doctorDTO) {
		return doctorService.updateDoctor(doctorDTO);
	}
}
