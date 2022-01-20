package com.example.spring_auth_server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "quiztype")
public class QuizType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    public int tid;

    @Column(name = "description")
    public String description;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quizType", cascade = CascadeType.MERGE)
//    public List<Question> questionList = new ArrayList<>();
}
