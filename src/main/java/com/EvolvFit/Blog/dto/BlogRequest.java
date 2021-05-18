package com.evolvfit.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BlogRequest {
    @NotBlank
    @Size(min = 5, max = 200)
    private String title;

    @NotBlank
    private String body;

    @Positive
    private long userId;
}
