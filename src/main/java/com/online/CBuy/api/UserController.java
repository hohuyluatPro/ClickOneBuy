package com.online.CBuy.api;

import com.online.CBuy.document.User;
import com.online.CBuy.dto.AffectedRowsDto;
import com.online.CBuy.dto.PostUserDto;
import com.online.CBuy.dto.PutUserDto;
import com.online.CBuy.repository.UserRepository;
import com.online.CBuy.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public AffectedRowsDto createUser(HttpServletRequest request,
                                      @Valid @RequestBody PostUserDto user) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.info("DIGO-Info: " + "đã tới đây");
        return userService.createUser(user);
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    //@IcodeAuthorize(permission = "manageDossierPayment")
    public AffectedRowsDto updatePayment(@PathVariable ObjectId id, @RequestBody PutUserDto sectorPayload) {
        return userService.updateUser(id, sectorPayload);
    }

    @DeleteMapping("/{id}")
    public AffectedRowsDto deleteUser(@PathVariable ObjectId id) {
        return userService.delete(id);
    }
}
