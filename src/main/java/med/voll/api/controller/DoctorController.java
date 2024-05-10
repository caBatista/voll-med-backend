package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorRequestDTO;
import med.voll.api.dto.DoctorResponseDTO;
import med.voll.api.model.Doctor;
import med.voll.api.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService doctorService;
	
	@PostMapping
	@Transactional
	public Doctor createDoctor(@RequestBody @Valid DoctorRequestDTO doctorDTO) {
		return doctorService.createDoctor(doctorDTO);
	}
	
	@GetMapping
	public List<DoctorResponseDTO> findAll(){
		return doctorService.findAll();
	}
}
