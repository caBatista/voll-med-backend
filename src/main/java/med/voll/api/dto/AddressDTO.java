package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
	@NotBlank
	String street,
	@Pattern(regexp = "\\d{5}-\\d{3}")
	@NotBlank
	String zipCode,
	
	Integer number,
	
	String complement,
	@NotBlank
	String city,
	@NotBlank
	String state
){}
