package com.example.spring_auth_server.entity;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserAnswers {
    Question question;
    List<Choice> choices;
    int answer;
}
