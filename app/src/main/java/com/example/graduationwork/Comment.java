package com.example.graduationwork;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    String text;

    public List<Comment> getReplies() {
        return replies;
    }

    public String getText() {
        return text;
    }

    List<Comment> replies;

    public Comment(String text) {
        this.text = text;
        this.replies = new ArrayList<>();
    }

    public void addReply(Comment reply) {
        this.replies.add(reply);
    }
}
