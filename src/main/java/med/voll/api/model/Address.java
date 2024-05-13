package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
	private String street;
	private String zipCode;
	private Integer number;
	private String complement;
	private String city;
	private String state;
	
	public void update(AddressDTO address) {
		if(address.street() != null)
			this.street = address.street();
		if(address.zipCode() != null)
			this.zipCode = address.zipCode();
		if(address.number() != null)
			this.number = address.number();
		if(address.complement() != null)
			this.complement = address.complement();
		if(address.city() != null)
			this.city = address.city();
		if(address.state() != null)
			this.state = address.state();
	}
}
