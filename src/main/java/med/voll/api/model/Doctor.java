package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.type.Specialty;

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
	
}
