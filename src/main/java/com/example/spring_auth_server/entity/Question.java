package com.example.spring_auth_server.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qid")
    public int qid;

    @Column(name = "tid")
    public int tid;

    @Column(name = "description")
    public String description;

    @Column(name = "status")
    public String status;
}
