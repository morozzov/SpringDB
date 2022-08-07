package com.example.springdb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerEntity {

    @Id
    private Long id;
    private String nickName;
    private boolean terminated;
    @Column(name = "\"profileInfo\"")
    private String profileInfo;
}
