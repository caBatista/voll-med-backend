package med.voll.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import med.voll.api.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("Voll.MED API")
					.withSubject(user.getUsername())
					.withClaim("id", user.getId())
					.withClaim("role", "USER")
					.withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00")))
					.sign(algorithm);
			
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error creating token");
		}
	}
	
	public String getToken(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}
	
	public String validateToken(String token) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("Voll.MED API")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Error validating token");
		}
	}
}
