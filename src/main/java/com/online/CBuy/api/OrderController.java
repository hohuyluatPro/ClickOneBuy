package com.online.CBuy.api;

import com.online.CBuy.document.Order;
import com.online.CBuy.document.Product;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Order.SetOrder;
import com.online.CBuy.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN'")
    public AffectedRowsDto postOrder(HttpServletRequest request, @RequestBody SetOrder postOrder){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.postOrder(postOrder);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN'")
    public AffectedRowsDto putOrder(HttpServletRequest request, @PathVariable String id, @RequestBody SetOrder putOrder){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.putOrder(id, putOrder);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Order> getAllOrder(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.getListAllOrder();
    }

    @GetMapping("/{userId}/--getByUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Order> getRateByUserId(HttpServletRequest request, @PathVariable String userId){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.getOrderByUserId(userId);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Order getOrder(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.getOrder(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteOrder(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OrderController.class);
        logger.info("DIGO-Info: " + requestPath);
        return orderService.deleteOrder(id);
    }
}
