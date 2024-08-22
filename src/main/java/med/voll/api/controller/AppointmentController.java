package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentRequestDTO;
import med.voll.api.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;
	
	@PostMapping
	@Transactional
	public ResponseEntity createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
		var appointment = appointmentService.createAppointment(appointmentRequestDTO);
		return ResponseEntity.ok(appointment);
	}
}
