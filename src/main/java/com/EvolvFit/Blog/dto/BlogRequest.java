package com.EvolvFit.Blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequest {
    private String title;
    private String body;
    private long userId;
}
