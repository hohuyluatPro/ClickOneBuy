package com.online.CBuy.api;

import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.GetAccountDto;
import com.online.CBuy.pojo.Account.PostAccount;
import com.online.CBuy.pojo.Account.PutAccount;
import com.online.CBuy.repository.AccountRepository;
import com.online.CBuy.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

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
    public List<GetAccountDto> getAllAccounts(HttpServletRequest request) {
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
    public AffectedRowsDto updateAccount(HttpServletRequest request, @PathVariable String id, @RequestBody PutAccount updatedAccount) {
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
