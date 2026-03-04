package com.vedant.LifeOps.controller;


import com.vedant.LifeOps.dto.AuthResponse;
import com.vedant.LifeOps.dto.LoginRequest;
import com.vedant.LifeOps.model.RefreshToken;
import com.vedant.LifeOps.model.Role;
import com.vedant.LifeOps.model.User;
import com.vedant.LifeOps.repo.UserRepo;
import com.vedant.LifeOps.security.JwtService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import com.vedant.LifeOps.service.RefreshTokenService;
import com.vedant.LifeOps.repo.RefreshTokenRepo;
import com.vedant.LifeOps.dto.AuthResponse;
import com.vedant.LifeOps.dto.RegisterDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepo refreshTokenRepo;


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepo userRepo, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService, RefreshTokenRepo refreshTokenRepo){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepo = refreshTokenRepo;
    }

    @PostMapping("/register")
    public String register (@RequestBody RegisterDto request){
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists!";
        }

        User user = User.builder().username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepo.findByUsername(request.getUsername()).get();
        String accessToken = jwtService.generateToken(user.getUsername(), user.getRole().name());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken()));

    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestParam String refreshToken) {

        RefreshToken token = refreshTokenRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshTokenService.verifyExpiration(token);

        String newAccessToken = jwtService.generateToken(
                token.getUser().getUsername(),
                token.getUser().getRole().name()
        );

        return ResponseEntity.ok(
                new AuthResponse(newAccessToken, refreshToken)
        );
    }


}
