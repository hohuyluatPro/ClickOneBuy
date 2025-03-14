package com.online.CBuy.api;

import com.online.CBuy.dto.LoginDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/auth/token")
public class TokenController {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto credentials) {
        if (credentials.getUsername() == null || credentials.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username or password is missing"));
        }

        // URL lấy authentication_code từ Keycloak
        String tokenUrl = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Gửi yêu cầu xác thực đến Keycloak
        String requestBody = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&username=" + credentials.getUsername() +
                "&password=" + credentials.getPassword() +
                "&grant_type=password";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);
            if (response.getBody() != null && response.getBody().containsKey("access_token")) {
                return ResponseEntity.ok(Map.of("authentication_code", response.getBody().get("access_token").toString()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication failed"));
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody Map<String, String> request) {
        String authenticationCode = request.get("code");

        if (authenticationCode == null || authenticationCode.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid authentication code"));
        }

        // Gửi yêu cầu đến Keycloak để đổi authentication_code lấy access_token
        String tokenUrl = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&code=" + authenticationCode +
                "&grant_type=authorization_code" +
                "&redirect_uri=http://localhost:4200/";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Failed to retrieve access token"));
        }
    }
}

