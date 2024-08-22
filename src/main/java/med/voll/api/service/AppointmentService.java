package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AppointmentRequestDTO;
import med.voll.api.dto.AppointmentResponseDTO;
import med.voll.api.exception.AppointmentCreationException;
import med.voll.api.model.Appointment;
import med.voll.api.model.Doctor;
import med.voll.api.model.Patient;
import med.voll.api.repository.AppointmentRepository;
import med.voll.api.repository.DoctorRepository;
import med.voll.api.repository.PatientRepository;
import med.voll.api.type.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	private final AppointmentRepository appointmentRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		var appointmentDate = appointmentRequestDTO.date();
		
		if(appointmentDate.isBefore(LocalDateTime.now().plusMinutes(30))){
			throw new AppointmentCreationException("Date must be at least 30 minutes in the future");
		} else if (appointmentDate.getHour() < 7 ||
				appointmentDate.getHour() > 18 ||
				(appointmentDate.getHour() == 18 && appointmentDate.getMinute() > 0)){
			throw new AppointmentCreationException("Date must be between 7am and 6pm");
		}
		
		var patient = patientRepository.findByIdAndActiveTrue(appointmentRequestDTO.patientId())
				.orElseThrow(() -> new AppointmentCreationException("Patient not found"));
		
		validatePatientAppointments(patient, appointmentDate);
		
		Doctor doctor;
		
		if(appointmentRequestDTO.doctorId() != null){
			doctor = doctorRepository.findByIdAndActiveTrue(appointmentRequestDTO.doctorId())
					.orElseThrow(() -> new AppointmentCreationException("Doctor not found"));
			
			checkDoctorsAvailability(doctor, appointmentDate);
		} else if (appointmentRequestDTO.doctorSpecialty() != null){
			doctor = findAvailableDoctor(appointmentRequestDTO.date(), appointmentRequestDTO.doctorSpecialty());
		} else {
			throw new AppointmentCreationException("You need to select either a doctor or a specialty");
		}
		
		var appointment = Appointment.builder()
				.patient(patient)
				.doctor(doctor)
				.date(appointmentRequestDTO.date())
				.build();
		
		var savedAppointment = appointmentRepository.save(appointment);
		
		return new AppointmentResponseDTO(savedAppointment);
	}
	
	private void checkDoctorsAvailability(Doctor doctor, LocalDateTime appointmentDate) {
		var start = appointmentDate.minusHours(1);
		var end = appointmentDate.plusHours(1);
		
		var doctorAppointment = appointmentRepository.findDoctorAppointments(doctor.getId(), start, end);
		
		if(doctorAppointment.isPresent()){
			throw new AppointmentCreationException("The doctor is not available at this time");
		}
	}
	
	private void validatePatientAppointments(Patient patient, LocalDateTime appointmentDate) {
		
		var patientAppointments = appointmentRepository.findPatientAppointments(patient.getId(), appointmentDate);
		
		if(patientAppointments.isPresent()){
			throw new AppointmentCreationException("Patient already has an appointment at this day");
		}
	}
	
	private Doctor findAvailableDoctor(LocalDateTime appointmentDate, Specialty specialty) {
		var start = appointmentDate.minusHours(1);
		var end = appointmentDate.plusHours(1);
		
		var availableDoctors = doctorRepository.findAvailableDoctorsBySpecialty(start, end, specialty);
		
		if(availableDoctors.isEmpty()){
			throw new AppointmentCreationException("No available doctors specialized on " + specialty);
		} else {
			Random random = new Random();
			return availableDoctors.get(random.nextInt(availableDoctors.size()));
		}
	}
	
	public Page<AppointmentResponseDTO> findAll(Pageable pageable) {
		var appointmentsPage = appointmentRepository.findAll(pageable);
		
		return appointmentsPage.map(AppointmentResponseDTO::new);
	}
	
	public AppointmentResponseDTO findById(Long id) {
		var appointmentOptional = appointmentRepository.findById(id);
		
		return appointmentOptional.map(AppointmentResponseDTO::new).orElse(null);
	}
}
