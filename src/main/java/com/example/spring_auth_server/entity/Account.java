package com.example.spring_auth_server.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="useraccount")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    public int userID;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String pwd;

    @Column(name = "usergroup")
    public String usergroup;

    @Override
    public String toString(){
        return String.format("userID=%s, " +
                "username=%s, " +
                "password=%s " +
                "usergroup=%s", userID, username, pwd, usergroup);
    }
}

