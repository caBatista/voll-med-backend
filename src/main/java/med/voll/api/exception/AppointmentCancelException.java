package med.voll.api.exception;

public class AppointmentCancelException extends RuntimeException {
	public AppointmentCancelException(String message) {
		super(message);
	}
}
