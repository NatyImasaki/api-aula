package alura.med.voll.api.infra.security;

import alura.med.voll.api.domain.Usuario.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    public String gerarToken(UsuarioEntity usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234567");
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpericao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("\"erro ao gerrar token jwt\", exception");
        }
    }

    private Instant dataExpericao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
