package com.EvolvFit.Blog.controller;

import com.EvolvFit.Blog.dto.BlogRequest;
import com.EvolvFit.Blog.exception.ResourceNotFoundException;
import com.EvolvFit.Blog.model.Blog;
import com.EvolvFit.Blog.model.User;
import com.EvolvFit.Blog.repository.BlogRepository;
import com.EvolvFit.Blog.repository.CommentRepository;
import com.EvolvFit.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/blogs")
    public List<Blog> getListOfBlogs() {
        return blogRepository.findAll();
    }

    @GetMapping("/blogs/{blogId}")
    public Blog getBlogById(@PathVariable(name = "blogId") Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResourceNotFoundException("could not find blog " + id);
        } else {
            return optionalBlog.get();
        }

    }

    @PostMapping("/blogs")
    public Blog saveBlog(@Valid @RequestBody BlogRequest blogRequest) {
        Optional<User> optionalUser = userRepository.findById(blogRequest.getUserId());
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("could not find user " + blogRequest.getUserId());
        }
        Blog.BlogBuilder blogBuilder = Blog.builder();
        blogBuilder.title(blogRequest.getTitle());
        blogBuilder.body(blogRequest.getBody());
        blogBuilder.postedBy(optionalUser.get());
        return blogRepository.save(blogBuilder.build());
    }

    @PutMapping("/blogs/{blogId}")
    public Blog updateBlog(@PathVariable(name = "blogId") Long id, @Valid @RequestBody BlogRequest blogRequest) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResourceNotFoundException("could not find blog " + id);
        } else {
            Optional<User> optionalUser = userRepository.findById(blogRequest.getUserId());
            if (optionalUser.isEmpty()) {
                throw new ResourceNotFoundException("could not find user " + blogRequest.getUserId());
            }
            Blog blog = optionalBlog.get();
            blog.setTitle(blogRequest.getTitle());
            blog.setBody(blogRequest.getBody());
            blog.setPostedBy(optionalUser.get());
            return blogRepository.save(blog);
        }
    }

    @DeleteMapping("/blogs/{blogId}")
    public ResponseEntity<Blog> removeBlog(@PathVariable(name = "blogId") Long id) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResourceNotFoundException("could not find blog "  +  id);
        } else {
            blogRepository.delete(optionalBlog.get());
            return ResponseEntity.ok(optionalBlog.get());
        }
    }
}