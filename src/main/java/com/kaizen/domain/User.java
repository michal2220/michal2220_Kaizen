package com.kaizen.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    private int birgade;

    @OneToMany(
            targetEntity = Kaizen.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Kaizen> kaizen = new ArrayList<>();
}
