package med.voll.api.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HttpExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<String> errors = e.getFieldErrors().stream()
			.map(error -> error.getField() + " " + error.getDefaultMessage())
			.toList();
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(JWTVerificationException.class)
	public ResponseEntity handleJWTVerificationException(){
		return ResponseEntity.badRequest().body("Invalid token");
	}
	
	@ExceptionHandler(JWTCreationException.class)
	public ResponseEntity handleJWTCreationException(){
		return ResponseEntity.badRequest().body("Error creating token");
	}
	
	@ExceptionHandler(ObjectUpdateException.class)
	public ResponseEntity handleObjectUpdateException(ObjectUpdateException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(ObjectDeleteException.class)
	public ResponseEntity handleObjectDeleteException(ObjectDeleteException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(AppointmentCreationException.class)
	public ResponseEntity handleAppointmentCreationException(AppointmentCreationException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity handleHttpMessageNotReadableException(){
		return ResponseEntity.badRequest().body("Invalid Parameters");
	}
	
	@ExceptionHandler(AppointmentCancelException.class)
	public ResponseEntity handleAppointmentCreationException(AppointmentCancelException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
