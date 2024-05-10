package med.voll.api.dto;

public record AddressDTO(
	String street,
	String zipCode,
	Integer number,
	String complement,
	String city,
	String state
){}
