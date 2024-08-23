package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements AppointmentCreationValidationInterface {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		
		if(appointmentCrRequestDTO.doctorId() != null) {
			var doctorId = appointmentCrRequestDTO.doctorId();
			var doctor = doctorRepository.findByIdAndActiveTrue(doctorId);
			
			if(doctor.isEmpty()){
				throw new AppointmentCreationException("Invalid doctor");
			}
		}
	}
}
