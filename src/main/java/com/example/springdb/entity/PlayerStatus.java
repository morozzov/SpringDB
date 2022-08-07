package com.example.springdb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerStatus {

    ACTIVE(true),
    INACTIVE(false);

    private boolean val;
}
