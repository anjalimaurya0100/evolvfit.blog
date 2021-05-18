package com.EvolvFit.Blog.controller;

import com.EvolvFit.Blog.dto.CommentRequest;
import com.EvolvFit.Blog.exception.ResourceNotFoundException;
import com.EvolvFit.Blog.model.Blog;
import com.EvolvFit.Blog.model.Comment;
import com.EvolvFit.Blog.model.User;
import com.EvolvFit.Blog.repository.BlogRepository;
import com.EvolvFit.Blog.repository.CommentRepository;
import com.EvolvFit.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/blogs/{blogId}/comments")
    public Comment addComment(@PathVariable(name = "blogId") Long id, @Valid @RequestBody CommentRequest commentRequest) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResourceNotFoundException("could not find Blog "  +  id);
        } else {
            Optional<User> optionalUser = userRepository.findById(commentRequest.getUserId());
            if (optionalUser.isEmpty()) {
                throw new ResourceNotFoundException("could not find user  " +  commentRequest.getUserId());
            }
            Comment.CommentBuilder commentBuilder = Comment.builder();
            commentBuilder.blog(optionalBlog.get());
            commentBuilder.body(commentRequest.getBody());
            commentBuilder.commentedBy(optionalUser.get());
            return commentRepository.save(commentBuilder.build());
        }
    }

    @GetMapping("/blogs/{blogId}/comments")
    public List<Comment> getAllComments(@PathVariable(name = "blogId") Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResourceNotFoundException("could not found Blog "  +  id);
        } else {
            return commentRepository.findByBlogId(id);
        }
    }
}
