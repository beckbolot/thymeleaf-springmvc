package com.beck.dao;

import com.beck.entity.Comment;

import java.util.List;

public interface CommentDAO {

    void addComment(Comment comment);

    List<Comment> getAllComments();
}
