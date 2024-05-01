package MobileServer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain chain) throws IOException, ServletException {
        // look for Bearer auth header
        System.out.println("Test string");
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
//        final String username = jwtTokenService.validateTokenAndGetUsername(token);
//        if (username == null) {
//            // validation failed or token expired
//            chain.doFilter(request, response);
//            return;
//        }
        String username;
        try {
            final JWTVerifier verifier = jwtTokenService.getVerifier();
//            final Instant now = Instant.now();
//            Date dt = JWT.decode(token).getExpiresAt();
//            Instant i2 = JWT.decode(token).getExpiresAtAsInstant();

//            if (now.isAfter(i2)) {
//                throw new UnauthorizedException("Access is denied.");
//            }
            DecodedJWT jwt = verifier.verify(token);
            username = jwt.getSubject();
//            Date date = jwt.getExpiresAt();
//            Instant i1 = jwt.getExpiresAtAsInstant();
//            Instant i2 = jwt.getNotBeforeAsInstant();
//            Date i3 = jwt.getNotBefore();
        } catch (final TokenExpiredException expiredException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token is expired");
//            chain.doFilter(request, response);
            return;
        } catch (final JWTVerificationException verificationEx) {
            chain.doFilter(request, response);
            return;
        }

        // set user details on spring security context
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // continue with authenticated user
        chain.doFilter(request, response);
    }

}
