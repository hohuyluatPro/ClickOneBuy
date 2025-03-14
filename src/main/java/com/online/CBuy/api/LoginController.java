package com.online.CBuy.api;

import com.online.CBuy.document.Account;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.LoginDto;
import com.online.CBuy.pojo.Account.PostAccount;
import com.online.CBuy.repository.AccountRepository;
import com.online.CBuy.service.AccountService;
import com.online.CBuy.service.JwtBlacklistService;
import com.online.CBuy.tUtils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public AffectedRowsDto register(HttpServletRequest request, @RequestBody PostAccount account) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.postAccount(account);
    }


    @PostMapping("/login-v3")
    public Map<String, String> loginv2(@RequestBody LoginDto credentials) {
        // Trả về mã `authentication_code`
        return Map.of("authentication_code", "123456");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        jwtBlacklistService.addToBlacklist(jwtToken); // Thêm vào danh sách đen
        return ResponseEntity.ok("Logged out successfully.");
    }
}
