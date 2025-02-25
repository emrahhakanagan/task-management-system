package com.taskmanagement.dto.task;

import com.taskmanagement.model.Comment;

public class CommentDTO {
    private Long id;
    private String text;
    private Long authorId;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.authorId = comment.getUser().getId(); // Получаем автора комментария
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public Long getAuthorId() { return authorId; }
}