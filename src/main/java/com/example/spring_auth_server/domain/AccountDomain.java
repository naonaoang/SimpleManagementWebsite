package com.example.spring_auth_server.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccountDomain {
    public String username;
    public String pwd;
}
