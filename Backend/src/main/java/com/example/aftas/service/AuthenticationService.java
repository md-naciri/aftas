package com.example.aftas.service;

import com.example.aftas.security.dao.request.SignInRequest;
import com.example.aftas.security.dao.request.SignUpRequest;
import com.example.aftas.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
