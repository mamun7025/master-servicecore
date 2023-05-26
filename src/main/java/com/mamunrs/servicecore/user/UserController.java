package com.mamunrs.servicecore.user;

import com.mamunrs.servicecore.user.entity.User;
import com.mamunrs.servicecore.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }


}
