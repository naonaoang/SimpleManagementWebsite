package com.example.spring_auth_server.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    public int fid;

    @Column(name = "tid")
    public int tid;

    @Column(name = "rating")
    public int rating;

    @Column(name = "description")
    public String description;
}
