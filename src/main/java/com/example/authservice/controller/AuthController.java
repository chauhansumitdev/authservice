package com.example.authservice.controller;


import com.example.authservice.dto.Message;
import com.example.authservice.dto.Request;
import com.example.authservice.dto.Response;
import com.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }



    @PostMapping("/refresh")
    public Response refresh(@RequestBody Request request) throws Exception{
        return authService.refresh(request);
    }



    @PostMapping()
    public Response generateTokens(@RequestBody Request reqest){
        return authService.generateTokens(reqest);
    }



    @GetMapping("/{token}")
    public Response verify(@PathVariable String token) throws  Exception{
        return authService.vefify(token);
    }


}
