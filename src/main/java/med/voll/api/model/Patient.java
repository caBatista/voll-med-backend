package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;

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
}
