package med.voll.api.dto;

import med.voll.api.type.Specialty;

public record DoctorRequestDTO(
	String name,
	String email,
	String phoneNumber,
	String crm,
	Specialty specialty,
	AddressDTO address
){}
