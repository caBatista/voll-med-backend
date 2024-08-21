package med.voll.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.dto.PatientUpRequestDTO;

@Entity
@Table(name = "patients")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phoneNumber;
	private String cpf;
	private Address address;
	
	private boolean active = true;
	
	public void update(@Valid PatientUpRequestDTO patientDTO) {
		if(patientDTO.name() != null)
			this.name = patientDTO.name();
		if(patientDTO.phoneNumber() != null)
			this.phoneNumber = patientDTO.phoneNumber();
		if(patientDTO.address() != null)
			this.address.update(patientDTO.address());
		
	}
}
