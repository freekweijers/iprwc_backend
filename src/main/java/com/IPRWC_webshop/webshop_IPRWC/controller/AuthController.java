package com.IPRWC_webshop.webshop_IPRWC.controller;


import com.IPRWC_webshop.webshop_IPRWC.dto.AuthRequestDTO;
import com.IPRWC_webshop.webshop_IPRWC.dto.AuthResponseDTO;
import com.IPRWC_webshop.webshop_IPRWC.model.ApiResponse;
import com.IPRWC_webshop.webshop_IPRWC.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ApiResponse<AuthResponseDTO> login(@RequestBody AuthRequestDTO loginDTO) {
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());

        return new ApiResponse<>(new AuthResponseDTO(token));
    }
}
