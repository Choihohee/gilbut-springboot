package com.gilbut.gilbut.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Cleanup;

@Entity
public class Member {
    @Id@GeneratedValue
    private Long member_id;

    @Column
    private String email;

    @Column
    private String password;

    public Member(Long member_id, String email, String password) {
        this.member_id = member_id;
        this.email = email;
        this.password = password;
    }

}
