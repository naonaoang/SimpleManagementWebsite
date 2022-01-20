package com.example.spring_auth_server.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryUnit {
    public Question question;
    public List<Choice> choices;
    public Choice answer;
    public Choice pick;

}
