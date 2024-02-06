package com.example.aftas.security.dao.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String username;
    private String password;
}
