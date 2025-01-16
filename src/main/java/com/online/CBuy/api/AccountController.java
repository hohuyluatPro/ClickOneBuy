package com.online.CBuy.api;

import com.online.CBuy.document.Account;
import com.online.CBuy.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // CREATE: Thêm mới tài khoản
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        // Kiểm tra nếu username đã tồn tại
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        // Mã hóa mật khẩu
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    // READ: Lấy danh sách tất cả tài khoản
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    // READ: Lấy thông tin tài khoản theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get());
        } else {
            return ResponseEntity.status(404).body("Account not found");
        }
    }

    // UPDATE: Cập nhật tài khoản
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setUsername(updatedAccount.getUsername());
            if (updatedAccount.getPassword() != null && !updatedAccount.getPassword().isEmpty()) {
                account.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            }
            Account savedAccount = accountRepository.save(account);
            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.status(404).body("Account not found");
        }
    }

    // DELETE: Xóa tài khoản
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable String id) {
        Optional<Account> existingAccount = accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            accountRepository.deleteById(id);
            return ResponseEntity.ok("Account deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Account not found");
        }
    }
}
