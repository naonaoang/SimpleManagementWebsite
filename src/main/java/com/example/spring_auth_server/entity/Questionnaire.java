package com.example.spring_auth_server.entity;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Questionnaire {

    public List<UserAnswers> userAnswers;
}
