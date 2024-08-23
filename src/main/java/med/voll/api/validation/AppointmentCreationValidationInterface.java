package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import org.springframework.stereotype.Component;

@Component
public interface AppointmentCreationValidationInterface {
	void validate(AppointmentCrRequestDTO appointmentCrRequestDTO);
}
