package com.example.springdb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weapon_seq")
    @SequenceGenerator(name = "weapon_seq", sequenceName = "weapon_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String detailedName;

    private Integer range;

    private Integer damage;

    private Boolean broken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

}
