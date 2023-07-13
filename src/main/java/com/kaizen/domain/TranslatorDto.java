package com.kaizen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDto {

    private String protocol;
    private int code;
    private String message;
    private String url;
}
