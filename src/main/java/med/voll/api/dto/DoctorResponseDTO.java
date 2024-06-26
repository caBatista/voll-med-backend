package med.voll.api.dto;

import med.voll.api.model.Doctor;
import med.voll.api.type.Specialty;

public record DoctorResponseDTO(
	long id,
	String name,
	String email,
	String crm,
	String phoneNumber,
	Specialty specialty
){
	public DoctorResponseDTO(Doctor doctor){
		this(
			doctor.getId(),
			doctor.getName(),
			doctor.getEmail(),
			doctor.getCrm(),
			doctor.getPhoneNumber(),
			doctor.getSpecialty()
		);
	}
}
