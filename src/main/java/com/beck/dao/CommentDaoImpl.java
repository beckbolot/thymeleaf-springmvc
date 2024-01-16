package com.beck.dao;

import com.beck.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDAO{

    private SessionFactory sessionFactory;
    private static Logger logger = LoggerFactory.getLogger(BasketOrderDaoImpl.class);

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().persist(comment);
        logger.info("Comment was added: " + comment);

    }

    @Override
    public List<Comment> getAllComments() {
        return sessionFactory.getCurrentSession().createQuery("from Comment").list();
    }
}
