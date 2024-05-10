package med.voll.api.dto;

import med.voll.api.model.Doctor;
import med.voll.api.type.Specialty;

public record DoctorResponseDTO(
	String name,
	String email,
	String crm,
	Specialty specialty
){
	public DoctorResponseDTO(Doctor doctor){
		this(
			doctor.getName(),
			doctor.getEmail(),
			doctor.getCrm(),
			doctor.getSpecialty()
		);
	}
	
}
