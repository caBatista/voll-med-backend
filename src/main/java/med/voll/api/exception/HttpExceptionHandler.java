package med.voll.api.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity handleJWTVerificationException(JWTVerificationException e){
		return ResponseEntity.badRequest().body("Invalid token");
	}
	
	@ExceptionHandler(JWTCreationException.class)
	public ResponseEntity handleJWTCreationException(JWTCreationException e){
		return ResponseEntity.badRequest().body("Error creating token");
	}
}
