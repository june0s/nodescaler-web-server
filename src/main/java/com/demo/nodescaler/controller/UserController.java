package com.demo.nodescaler.controller;

import com.demo.nodescaler.dto.AuthResponseDto;
import com.demo.nodescaler.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        log.info("/api/auth/hello");
        return "Hello this endpoint is not secure :)";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {

        log.info("/api/auth/login");
        log.info(loginDto.toString());

        AuthResponseDto authResponseDTO = new AuthResponseDto("token");
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
}
