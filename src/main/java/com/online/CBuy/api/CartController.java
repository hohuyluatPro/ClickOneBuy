package com.online.CBuy.api;


import com.online.CBuy.document.Cart;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Cart.SetCart;
import com.online.CBuy.repository.CartRepository;
import com.online.CBuy.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto postCart(HttpServletRequest request, @RequestBody SetCart postCart){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.postCart(postCart);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putCart(HttpServletRequest request, @PathVariable String id, @RequestBody SetCart postCart){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.putCart(id, postCart);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putCartByUserId(HttpServletRequest request, @RequestBody SetCart postCart){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.putCartByUserId(postCart);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Cart> getAllCart(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.getListAllCart();
    }

    @GetMapping("/{userId}/--getByUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Cart getCartByUserId(HttpServletRequest request, @PathVariable String userId){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Cart getCart(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.getCart(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteCart(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(CartController.class);
        logger.info("DIGO-Info: " + requestPath);
        return cartService.deleteCart(id);
    }
}
