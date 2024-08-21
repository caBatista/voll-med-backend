package med.voll.api.dto;

import med.voll.api.model.Patient;

public record PatientResponseDTO(
		long id,
		String name,
		String email,
		String cpf,
		String phoneNumber
){
	public PatientResponseDTO(Patient patient){
		this(
				patient.getId(),
				patient.getName(),
				patient.getEmail(),
				patient.getCpf(),
				patient.getPhoneNumber()
		);
	}
}
