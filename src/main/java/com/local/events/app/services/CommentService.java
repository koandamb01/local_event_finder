package com.local.events.app.services;

import com.local.events.app.models.Comment;
import com.local.events.app.models.Event;
import com.local.events.app.models.User;
import com.local.events.app.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepo;


    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    // create a new comment
    public Comment createComment(Comment comment, User user, Event event){
        comment.setUser(user);
        comment.setEvent(event);
        return this.commentRepo.save(comment);
    }

    // get all comment
    public List<Comment> findAllComments(){ return this.commentRepo.findAll(); }

    // get a comment by id
    public Comment findCommentById(Long id){
        Optional<Comment> res = this.commentRepo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }
    }
}
