package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentClRequestDTO;
import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.service.AppointmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

	private final AppointmentService appointmentService;
	
	@PostMapping
	@Transactional
	public ResponseEntity createAppointment(@Valid @RequestBody AppointmentCrRequestDTO appointmentCrRequestDTO) {
		var appointment = appointmentService.createAppointment(appointmentCrRequestDTO);
		
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
	
	@PostMapping("{id}/cancel")
	@Transactional
	public ResponseEntity cancelAppointment(@PathVariable Long id, @RequestBody AppointmentClRequestDTO appointmentClRequestDTO) {
		appointmentService.cancelAppointment(id, appointmentClRequestDTO);
		
		return ResponseEntity.noContent().build();
	}
}
