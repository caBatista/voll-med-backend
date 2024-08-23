package med.voll.api.validation;

import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentCreationValidationInterface {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public void validate(AppointmentCrRequestDTO appointmentCrRequestDTO){
		var patientId = appointmentCrRequestDTO.patientId();
		var patient = patientRepository.findByIdAndActiveTrue(patientId);
		
		if(patient.isEmpty()){
			throw new AppointmentCreationException("Invalid patient");
		}
	}
}
