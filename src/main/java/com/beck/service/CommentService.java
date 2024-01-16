package com.beck.service;

import com.beck.entity.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment,String name);

    List<Comment> getAllComments();
}
