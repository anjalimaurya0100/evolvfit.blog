package com.evolvfit.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ReplyRequest {
    @NotBlank
    private String body;

    @Positive
    private Long userId;
}
