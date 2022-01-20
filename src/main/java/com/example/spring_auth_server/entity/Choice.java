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
@Table(name = "choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    public int cid;

    @Column(name = "qid")
    public int qid;

    @Column(name = "isAnswer")
    public boolean answer;

    @Column(name = "description")
    public String description;
}
