package com.kaizen.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int userId;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String lastname;

    @Column
    @NotNull
    private int brigade;

    @OneToMany(
            targetEntity = Kaizen.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Kaizen> kaizen = new ArrayList<>();

    public User(int userId, String name, String lastname, int brigade, List<Kaizen> kaizen) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.brigade = brigade;
        this.kaizen = kaizen;
    }

    public User(String name, String lastname, int brigade) {
        this.name = name;
        this.lastname = lastname;
        this.brigade = brigade;
    }
}
