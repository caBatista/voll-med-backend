package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentRequestDTO;
import med.voll.api.dto.AppointmentResponseDTO;
import med.voll.api.model.Appointment;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentRepository appointmentRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		var patient = patientRepository.findByIdAndActiveTrue(appointmentRequestDTO.patientId())
				.orElseThrow();
		
		var doctor = doctorRepository.findByIdAndActiveTrue(appointmentRequestDTO.doctorId())
				.orElseThrow();
		
		var appointment = Appointment.builder()
				.patient(patient)
				.doctor(doctor)
				.date(appointmentRequestDTO.date())
				.build();
		
		var savedAppointment = appointmentRepository.save(appointment);
		
		return new AppointmentResponseDTO(savedAppointment);
	}
}
