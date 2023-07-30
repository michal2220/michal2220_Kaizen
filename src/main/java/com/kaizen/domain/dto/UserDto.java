package com.kaizen.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private int userId;
    private String name;
    private String lastname;
    private int brigade;
    private List<Integer> kaizenList = new ArrayList<>();

    public UserDto(int userId, String name, String lastname, int brigade, List<Integer> kaizenList) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.brigade = brigade;
        this.kaizenList = kaizenList;
    }

    public UserDto() {
    }
}
