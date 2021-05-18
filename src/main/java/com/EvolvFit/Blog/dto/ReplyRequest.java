package com.evolvfit.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyRequest {
    private String body;
    private Long userId;
}
