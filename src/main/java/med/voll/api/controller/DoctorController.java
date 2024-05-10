package med.voll.api.controller;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DoctorRequestDTO;
import med.voll.api.model.Doctor;
import med.voll.api.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService doctorService;
	
	@PostMapping
	public Doctor createDoctor(@RequestBody DoctorRequestDTO doctorDTO) {
		return doctorService.createDoctor(doctorDTO);
	}
}
