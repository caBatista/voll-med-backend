package med.voll.api.exception;

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
}
