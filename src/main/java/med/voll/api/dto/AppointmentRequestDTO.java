package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.type.Specialty;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
	Long doctorId,
	
	Specialty doctorSpecialty,
	
	@NotNull
	Long patientId,
	
	@NotNull
	LocalDateTime date
){}
