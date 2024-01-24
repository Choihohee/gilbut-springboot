package com.gilbut.gilbut.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id@GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

}
