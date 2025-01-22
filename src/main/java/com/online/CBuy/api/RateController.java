package com.online.CBuy.api;

import com.online.CBuy.document.Cart;
import com.online.CBuy.document.Rate;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Rate.SetRate;
import com.online.CBuy.service.RateService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateController {
    @Autowired
    private RateService rateService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto postRate(HttpServletRequest request, @RequestBody SetRate postRate){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.postRate(postRate);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putRateByUserId(HttpServletRequest request, @RequestBody SetRate putRate){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.putRateByProductIdAndUserId(putRate);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putRate(HttpServletRequest request, @PathVariable String id, @RequestBody SetRate putRate){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.putRate(id, putRate);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Rate> getAllRate(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.getListAllRate();
    }

    @GetMapping("/{userId}/--getByUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Rate> getRateByUserId(HttpServletRequest request, @PathVariable String userId){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.getRateByUserId(userId);
    }

    @GetMapping("/{productId}/--getByProduct")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Rate> getRateByProductId(HttpServletRequest request, @PathVariable String productId){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.getRateByProductId(productId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Rate getRate(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.getRate(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteRate(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(RateController.class);
        logger.info("DIGO-Info: " + requestPath);
        return rateService.deleteRate(id);
    }
}
