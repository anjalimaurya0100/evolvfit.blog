package com.EvolvFit.Blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String body;
    private long userId;
}
