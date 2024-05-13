package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.type.Specialty;

public record DoctorCrRequestDTO(
	@NotBlank
	String name,
	@Email
	@NotBlank
	String email,
	@NotBlank
	String phoneNumber,
	@Pattern(regexp = "\\d{5}-[A-Z]{2}")
	@NotBlank
	String crm,
	@NotNull
	Specialty specialty,
	@NotNull @Valid
	AddressDTO address
){}
