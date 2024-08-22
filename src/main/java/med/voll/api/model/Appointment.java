package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.type.CancelReason;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	private LocalDateTime date;
	
	private boolean active = true;
	
	@Enumerated(EnumType.STRING)
	private CancelReason cancelReason;
	
	public void cancel(CancelReason cancelReason) {
		this.active = false;
		this.cancelReason = cancelReason;
	}
}
