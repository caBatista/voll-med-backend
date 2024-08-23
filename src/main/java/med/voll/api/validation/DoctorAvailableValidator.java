package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorAvailableValidator implements AppointmentCreationValidationInterface {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO){
		if(appointmentCrRequestDTO.doctorId() != null) {
			var doctorId = appointmentCrRequestDTO.doctorId();
			var appointmentDate = appointmentCrRequestDTO.date();
			
			var startTime = appointmentDate.minusHours(1);
			var endTime = appointmentDate.plusHours(1);
			
			var doctorAppointments = appointmentRepository.findDoctorAppointments(doctorId, startTime, endTime);
			
			if(doctorAppointments.isPresent()){
				throw new AppointmentCreationException("Doctor is not available");
			}
		}
	}
}
