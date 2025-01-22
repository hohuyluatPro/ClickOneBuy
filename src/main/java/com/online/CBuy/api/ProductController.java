package com.online.CBuy.api;

import com.online.CBuy.document.Product;
import com.online.CBuy.document.Rate;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Product.SetProduct;
import com.online.CBuy.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto postProduct(HttpServletRequest request, @RequestBody SetProduct postProduct){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.postProduct(postProduct);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putProduct(HttpServletRequest request, @PathVariable String id, @RequestBody SetProduct putProduct){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.putProduct(id, putProduct);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Product> getAllProduct(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.getListAllProduct();
    }

    @GetMapping("/{userId}/--getBySeller")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Product> getRateByUserId(HttpServletRequest request, @PathVariable String userId){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.getProductBySellerId(userId);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Product getProduct(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteProduct(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        logger.info("DIGO-Info: " + requestPath);
        return productService.deleteProduct(id);
    }
}
