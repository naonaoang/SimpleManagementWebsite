package com.example.spring_auth_server.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    public int rid;

    @Column(name = "sid")
    public int sid;

    @Column(name = "qid")
    public int qid;

    @Column(name = "aid")
    public int aid;

    @Column(name = "cid")
    public int cid;
}
