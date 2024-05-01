package MobileServer.security;

import MobileServer.models.User;
import MobileServer.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SecurityController {
    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;


    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody @Valid final AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
       return auth(authenticationRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(@RequestBody AuthenticationResponse token) {
        String refreshToken = token.getRefreshToken();
        String username = jwtTokenService.validateTokenAndGetUsername(refreshToken);
        if (username == null) {
            return ResponseEntity.status(403).build();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        authenticationResponse.setRefreshToken(jwtTokenService.generateRefreshToken(userDetails));
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody User user) {
        service.createUser(user);
        return auth(new AuthenticationRequest(user.getLogin(), user.getPassword()));
    }

    @GetMapping("/findByLogin")
    public boolean findByLogin(@RequestParam(name = "login") String login) {
        return service.findUser(login);
    }

    private AuthenticationResponse auth(AuthenticationRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        authenticationResponse.setRefreshToken(jwtTokenService.generateRefreshToken(userDetails));
        return authenticationResponse;
    }


}
