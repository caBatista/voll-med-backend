package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AntecedenceValidator implements AppointmentCreationValidationInterface {
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		var appointmentDate = appointmentCrRequestDTO.date();
		
		if(appointmentDate.isBefore(LocalDateTime.now().plusMinutes(30))) {
			throw new AppointmentCreationException("Date must be at least 30 minutes on the future");
		}
	}
}
