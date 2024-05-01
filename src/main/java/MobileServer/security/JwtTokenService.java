package MobileServer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
@Getter
public class JwtTokenService {

    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofDays(1);
//    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofSeconds(30);
    private static final Duration JWT_REFRESH_TOKEN_VALIDITY = Duration.ofDays(7);

    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512);
    }

    public String generateRefreshToken(final UserDetails userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_REFRESH_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            final Instant now = Instant.now();
            Date dt = JWT.decode(token).getExpiresAt();
            Instant i2 = JWT.decode(token).getExpiresAtAsInstant();

            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getSubject();
            return username;
        } catch (final TokenExpiredException expiredException) {
          return null;
        } catch (final JWTVerificationException verificationEx) {
            return null;
        }
    }

}
