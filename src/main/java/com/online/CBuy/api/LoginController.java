package com.online.CBuy.api;

import com.online.CBuy.document.Account;
import com.online.CBuy.dto.LoginDto;
import com.online.CBuy.dto.PostAccount;
import com.online.CBuy.repository.AccountRepository;
import com.online.CBuy.tUtils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        // Mã hóa mật khẩu
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return ResponseEntity.ok("Account registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent() && passwordEncoder.matches(account.getPassword(), existingAccount.get().getPassword())) {
            String token = jwtUtil.generateToken(existingAccount.get());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
