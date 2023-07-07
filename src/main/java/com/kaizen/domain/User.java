package com.kaizen.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

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
    private String birgade;
}
