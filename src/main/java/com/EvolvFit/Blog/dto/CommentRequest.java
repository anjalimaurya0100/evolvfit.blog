package com.evolvfit.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String body;
    private long userId;
}
