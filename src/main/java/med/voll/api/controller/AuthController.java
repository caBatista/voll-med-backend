package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.JwtTokenDTO;
import med.voll.api.dto.UserLoginDTO;
import med.voll.api.model.User;
import med.voll.api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());
		var authentication = authenticationManager.authenticate(authenticationToken);
		
		var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());
		
		return ResponseEntity.ok(new JwtTokenDTO(jwtToken).token());
		
	}
}
