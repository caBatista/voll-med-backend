package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentRequestDTO;
import med.voll.api.service.AppointmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping
	public ResponseEntity findAll(@PageableDefault Pageable pageable) {
		var appointmentsPage = appointmentService.findAll(pageable);
		
		if(appointmentsPage.getTotalElements() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(appointmentsPage);
	}
	
	@GetMapping("{id}")
	public ResponseEntity findById(@PathVariable Long id) {
		var appointment = appointmentService.findById(id);
		
		if(appointment == null){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(appointment);
	}
}
