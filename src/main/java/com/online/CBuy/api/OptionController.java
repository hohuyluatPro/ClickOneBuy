package com.online.CBuy.api;


import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.pojo.Option.PostOption;
import com.online.CBuy.pojo.Option.PutOption;
import com.online.CBuy.repository.OptionRepository;
import com.online.CBuy.service.OptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.online.CBuy.document.Option;

import java.util.List;

@RestController
@RequestMapping("/api/option")
public class OptionController {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private OptionService optionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto postOption(HttpServletRequest request, @RequestBody PostOption postOption){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OptionController.class);
        logger.info("DIGO-Info: " + requestPath);
        return optionService.postOption(postOption);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto putOption(HttpServletRequest request,@PathVariable String id,  @RequestBody PutOption putOption){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OptionController.class);
        logger.info("DIGO-Info: " + requestPath);
        return optionService.putOption(id, putOption);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Option> getAllOption(HttpServletRequest request){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OptionController.class);
        logger.info("DIGO-Info: " + requestPath);
        return optionService.getListAllOption();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Option getOption(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OptionController.class);
        logger.info("DIGO-Info: " + requestPath);
        return optionService.getOption(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AffectedRowsDto deleteOption(HttpServletRequest request, @PathVariable String id){
        String requestPath = request.getMethod() + " " + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        Logger logger = LoggerFactory.getLogger(OptionController.class);
        logger.info("DIGO-Info: " + requestPath);
        return optionService.deleteAccount(id);
    }
}
