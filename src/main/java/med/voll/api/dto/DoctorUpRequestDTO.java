package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorUpRequestDTO(
	@NotNull
	long id,
	String name,
	String phoneNumber,
	@Valid
	AddressDTO address
) {}
