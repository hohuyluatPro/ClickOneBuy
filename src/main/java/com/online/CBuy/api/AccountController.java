package com.online.CBuy.api;

import com.online.CBuy.document.Account;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.GetAccountDto;
import com.online.CBuy.dto.PostAccount;
import com.online.CBuy.dto.PutUserDto;
import com.online.CBuy.repository.AccountRepository;
import com.online.CBuy.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // CREATE: Thêm mới tài khoản
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto createAccount(HttpServletRequest request, @RequestBody PostAccount account) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.postAccount(account);
    }

    // READ: Lấy danh sách tất cả tài khoản
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Account> getAllAccounts(HttpServletRequest request) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.getListAccount();
    }

    // READ: Lấy thông tin tài khoản theo ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public GetAccountDto getAccountById(HttpServletRequest request, @PathVariable String id) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.getAccount(id);
    }

    // UPDATE: Cập nhật tài khoản
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto updateAccount(HttpServletRequest request, @PathVariable String id, @RequestBody PutUserDto updatedAccount) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.putAccount(id, updatedAccount);
    }

    // DELETE: Xóa tài khoản
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteAccount(HttpServletRequest request, @PathVariable String id) {
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(AccountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return accountService.deleteAccount(id);
    }
}
