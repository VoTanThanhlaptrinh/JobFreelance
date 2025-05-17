package com.job_freelance_internal_db.api;

import com.job_freelance_internal_db.object.Response;
import com.job_freelance_internal_db.object.User;
import com.job_freelance_internal_db.object.dto.LoginDTO;
import com.job_freelance_internal_db.object.dto.RegisterDTO;
import com.job_freelance_internal_db.service.JwtService;
import com.job_freelance_internal_db.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/account", produces = "application/json")
@RequiredArgsConstructor
public class AccountAPI {
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<Response<Object>> doLogin(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<>(400,null,result.getAllErrors().get(0).getDefaultMessage()));
        }
        Response<Object> login = userService.checkUser(loginDTO.getUsername(), loginDTO.getPassword());
        if(login.getStatus() != 200){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(login);
        }
        final String token =  jwtService.generateToken((User) login.getData());
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(200,token,login.getMessage()));
    }
    @PostMapping("/register")
    public ResponseEntity<Response<Object>> doRegister(@Valid @RequestBody RegisterDTO registerDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response<Object>(400,null,result.getAllErrors().get(0).getDefaultMessage()));
        }
        Response<Object> register = userService.register(registerDTO.toUser());
        if(register.getStatus() != 200){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(register);
        }
        return ResponseEntity.status(HttpStatus.OK).body(register);
    }
    @GetMapping("/isLogin/{token}")
    public ResponseEntity<Response<Object>> isLogin(@PathVariable String token){
        if(!jwtService.isTokenExpired(token)){
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>(200,null,null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response<>(401,null,null));
    }
}
