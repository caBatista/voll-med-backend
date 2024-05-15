package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.DoctorUpRequestDTO;
import med.voll.api.type.Specialty;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "doctors", uniqueConstraints = {@UniqueConstraint(columnNames = {"crm"})})
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phoneNumber;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	
	@Embedded
	private Address address;
	
	private boolean active = true;
	
	public void update(DoctorUpRequestDTO doctorDTO) {
		if(doctorDTO.name() != null)
			this.name = doctorDTO.name();
		if(doctorDTO.phoneNumber() != null)
			this.phoneNumber = doctorDTO.phoneNumber();
		if(doctorDTO.address() != null)
			this.address.update(doctorDTO.address());
	}
	
	public void delete() {
		this.active = false;
		System.out.println("Doctor " + this.name + " deleted");
	}
}
