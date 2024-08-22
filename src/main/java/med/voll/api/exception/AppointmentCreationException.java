package med.voll.api.exception;

public class AppointmentCreationException extends RuntimeException {
  public AppointmentCreationException(String message) {
    super(message);
  }
}
