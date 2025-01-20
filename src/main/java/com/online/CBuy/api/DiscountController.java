package com.online.CBuy.api;

import com.online.CBuy.document.Discount;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Discount.SetDiscount;
import com.online.CBuy.repository.DiscountRepository;
import com.online.CBuy.service.DiscountService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountService discountService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto postDiscount(HttpServletRequest request, @RequestBody SetDiscount postDiscount){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(DiscountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return discountService.postDiscount(postDiscount);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putDiscount(HttpServletRequest request, @PathVariable String id, @RequestBody SetDiscount putDiscount){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(DiscountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return discountService.putDiscount(id, putDiscount);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Discount> getAllDiscount(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(DiscountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return discountService.getListAllDiscount();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Discount getDiscount(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(DiscountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return discountService.getDiscount(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteOption(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(DiscountController.class);
        logger.info("DIGO-Info: " + requestPath);
        return discountService.deleteAccount(id);
    }
}
