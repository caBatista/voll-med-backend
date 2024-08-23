package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import org.springframework.stereotype.Component;

@Component
public class WorkingHoursValidator implements AppointmentCreationValidationInterface {
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		var appointmentDate = appointmentCrRequestDTO.date();
		
		if (appointmentDate.getHour() < 7 ||
				appointmentDate.getHour() > 18 ||
				(appointmentDate.getHour() == 18 && appointmentDate.getMinute() > 0)){
			throw new AppointmentCreationException("Date must be between 7am and 6pm");
		}
	}
}
