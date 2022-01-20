package com.example.spring_auth_server.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    public int sID;

    @Column(name = "userID")
    public int userID;

    @Column(name = "typeID")
    public int typeID;

    @Column(name = "pass")
    public boolean pass;
}
