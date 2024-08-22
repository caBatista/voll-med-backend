package med.voll.api.dto;

import med.voll.api.model.Appointment;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
	Long id,
	String doctorName,
	String patientName,
	LocalDateTime date
) {
	public AppointmentResponseDTO(Appointment savedAppointment) {
		this(
				savedAppointment.getId(),
				savedAppointment.getDoctor().getName(),
				savedAppointment.getPatient().getName(),
				savedAppointment.getDate()
		);
	}
}
