package com.beck.controller;

import com.beck.entity.Comment;
import com.beck.entity.User;
import com.beck.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentsController{

    private final CommentService commentService;

    @GetMapping
    public String getComments(Model model){
        model.addAttribute("comment",new Comment());
        return "comments";
    }

    @PostMapping
    public String createComment(Model model, HttpSession session,
                                @Valid @ModelAttribute Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "comments";
        }

        User currentUser = (User) session.getAttribute("currentUser");
        commentService.addComment(comment, currentUser.getName());

        return "redirect:comment/comments";
    }

    @ModelAttribute
    public void addCommentsList(Model model){
        model.addAttribute("commentsList",commentService.getAllComments());
    }

}
