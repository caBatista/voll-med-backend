package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientAvailableValidator implements AppointmentCreationValidationInterface {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		var patientId = appointmentCrRequestDTO.patientId();
		var appointmentDate = appointmentCrRequestDTO.date();
		
		var patientAppointments = appointmentRepository.findPatientAppointments(patientId, appointmentDate);
		
		if(patientAppointments.isPresent()) {
			throw new AppointmentCreationException("Patient already have an appointment for this day");
		}
	}
}
