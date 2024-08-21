package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientCrRequestDTO(
	@NotBlank
	String name,
	@NotBlank
	String email,
	@NotBlank
	String phoneNumber,
	@NotBlank
	String cpf,
	@NotNull
	AddressDTO address
){}
