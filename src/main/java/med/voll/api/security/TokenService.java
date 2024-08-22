package med.voll.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
		var algorithm = Algorithm.HMAC256(secret);
		return JWT.create()
				.withIssuer("Voll.MED API")
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withClaim("role", "USER")
				.withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00")))
				.sign(algorithm);
	}
	
	public String getToken(HttpServletRequest request) {
		var token = request.getHeader("Authorization");
		if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}
	
	public String validateToken(String token) {
		var algorithm = Algorithm.HMAC256(secret);
		return JWT.require(algorithm)
				.withIssuer("Voll.MED API")
				.build()
				.verify(token)
				.getSubject();
	}
}
