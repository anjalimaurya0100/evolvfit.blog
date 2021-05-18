package com.evolvfit.blog.controller;

import com.evolvfit.blog.dto.ReplyRequest;
import com.evolvfit.blog.exception.ResourceNotFoundException;
import com.evolvfit.blog.model.Comment;
import com.evolvfit.blog.model.Reply;
import com.evolvfit.blog.model.User;
import com.evolvfit.blog.repository.CommentRepository;
import com.evolvfit.blog.repository.ReplyRepository;
import com.evolvfit.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ReplyController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/comments/{commentId}/replies")
    public List<Reply> getAllRepliesByCommentID(@PathVariable(name = "commentId") Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            throw new ResourceNotFoundException("could not find comment " + id);
        } else {

            return replyRepository.findByCommentId(id);
        }
    }

    @PostMapping("/comments/{commentId}/replies")
    public Reply saveReplyToComment(@PathVariable(name = "commentId") Long id, @Valid @RequestBody ReplyRequest replyRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            throw new ResourceNotFoundException("could not find comment " + id);
        } else {
            Optional<User> optionalUser = userRepository.findById(replyRequest.getUserId());
            if (optionalUser.isEmpty()) {
                throw new ResourceNotFoundException("could not find user " + replyRequest.getUserId());
            }
            Reply.ReplyBuilder replyBuilder = Reply.builder();
            replyBuilder.body(replyRequest.getBody());
            replyBuilder.user(optionalUser.get());
            replyBuilder.comment(optionalComment.get());
            return replyRepository.save(replyBuilder.build());
        }
    }
}
