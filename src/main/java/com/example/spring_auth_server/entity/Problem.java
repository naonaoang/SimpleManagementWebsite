package com.example.spring_auth_server.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Problem {
    public Question question;
    public List<Choice> choiceList;
    public Choice rightChoice;
}
