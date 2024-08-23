package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentClRequestDTO;
import med.voll.api.dto.AppointmentCrRequestDTO;
import med.voll.api.dto.AppointmentResponseDTO;
import med.voll.api.exception.AppointmentCancelException;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.validation.AppointmentCreationValidationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentRepository appointmentRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	
	@Autowired
	private List<AppointmentCreationValidationInterface> validators;
	
	public AppointmentResponseDTO createAppointment(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		validators.forEach(v -> v.validate(appointmentCrRequestDTO));
		
		var patientId = appointmentCrRequestDTO.patientId();
		var patient = patientRepository.getReferenceById(patientId);
		
		var doctor = findAvailableDoctor(appointmentCrRequestDTO);
		
		var appointment = Appointment.builder()
				.patient(patient)
				.doctor(doctor)
				.date(appointmentCrRequestDTO.date())
				.active(true)
				.build();
		
		var savedAppointment = appointmentRepository.save(appointment);
		
		return new AppointmentResponseDTO(savedAppointment);
	}
	
	private Doctor findAvailableDoctor(AppointmentCrRequestDTO appointmentCrRequestDTO) {
		var doctorId = appointmentCrRequestDTO.doctorId();
		
		if(doctorId != null) {
			return doctorRepository.getReferenceById(appointmentCrRequestDTO.doctorId());
		}
		
		var appointmentDate = appointmentCrRequestDTO.date();
		var specialty = appointmentCrRequestDTO.doctorSpecialty();
		
		var start = appointmentDate.minusHours(1);
		var end = appointmentDate.plusHours(1);
		
		if(specialty == null) {
			throw new AppointmentCreationException("You need to select either a doctor Specialty or Id");
		}
		
		var availableDoctors = doctorRepository.findAvailableDoctorsBySpecialty(start, end, specialty);
		
		if(availableDoctors.isEmpty()){
			throw new AppointmentCreationException("No available doctors specialized on " + specialty + " for the specified time");
		} else {
			Random random = new Random();
			return availableDoctors.get(random.nextInt(availableDoctors.size()));
		}
	}
	
	public Page<AppointmentResponseDTO> findAll(Pageable pageable) {
		var appointmentsPage = appointmentRepository.findAllByActiveTrue(pageable);
		
		return appointmentsPage.map(AppointmentResponseDTO::new);
	}
	
	public AppointmentResponseDTO findById(Long id) {
		var appointmentOptional = appointmentRepository.findById(id);
		
		return appointmentOptional.map(AppointmentResponseDTO::new).orElse(null);
	}
	
	public void cancelAppointment(Long id, AppointmentClRequestDTO appointmentClRequestDTO) {
		var appointment = appointmentRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new AppointmentCancelException("Invalid appointment"));
		
		if(appointmentClRequestDTO.cancelReason() == null){
			throw new AppointmentCancelException("You need to select a reason for canceling the appointment");
		}
		
		appointment.cancel(appointmentClRequestDTO.cancelReason());
	}
}
