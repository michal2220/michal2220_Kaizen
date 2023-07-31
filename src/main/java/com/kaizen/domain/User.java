package com.kaizen.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "User.findUsersByKaizenCountGreaterThan",
                query = "SELECT u FROM User u WHERE u.userId IN (SELECT k.user.userId FROM Kaizen k GROUP BY k.user.userId" +
                        " HAVING COUNT(k.user.userId) > :kaizenCount)"
        ),
        @NamedQuery(
                name = "User.findUsersByKaizenCountLessThan",
                query = "SELECT u FROM User u WHERE u.userId IN (SELECT k.user.userId FROM Kaizen k GROUP BY k.user.userId" +
                        " HAVING COUNT(k.user.userId) < :kaizenCount)"
        ),
        @NamedQuery(
                name = "User.findUsersByIdeaCount",
                query = "SELECT u FROM User u WHERE u.userId IN (SELECT k.user.userId FROM Kaizen k GROUP BY k.user.userId" +
                        " HAVING COUNT(k.user.userId) = :kaizenCount)"
        ),
        @NamedQuery(
                name = "User.findUsersByLastname",
                query = "SELECT u FROM User u WHERE u.lastname = :lastname"
        )
})

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", brigade=" + brigade +
                ", kaizen=" + kaizen +
                '}';
    }
}




















