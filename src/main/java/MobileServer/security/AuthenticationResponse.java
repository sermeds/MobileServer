package MobileServer.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

    private String accessToken;

    private String refreshToken;

}