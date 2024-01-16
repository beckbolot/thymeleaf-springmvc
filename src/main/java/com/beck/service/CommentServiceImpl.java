package com.beck.service;

import com.beck.dao.CommentDAO;
import com.beck.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional
    public void addComment(Comment comment, String name) {
        comment.setName(name);
        comment.setDate(new Date());
        commentDAO.addComment(comment);

    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }
}
