package com.example.authservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Request {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String jwt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;

}
