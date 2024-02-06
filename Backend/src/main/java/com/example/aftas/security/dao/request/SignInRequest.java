package com.example.aftas.security.dao.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
