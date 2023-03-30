package com.forex.controller;

import com.forex.dto.AuthRequest;
import com.forex.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    /**
     * gets the authentication (JWT) token.
     * @param request
     * return token if valid credential otherwise throw error
     * */
    @PostMapping("authentication")
    public ResponseEntity<Map<String, String>> authentication(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwtService.generateToken(request.getUsername()));
            return new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("Invalid username, password combination.");
        }
    }
}
