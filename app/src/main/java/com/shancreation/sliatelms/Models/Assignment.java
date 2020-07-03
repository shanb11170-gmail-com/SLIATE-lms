package com.shancreation.sliatelms.Models;

import com.google.firebase.Timestamp;

import java.util.List;

public class Assignment {

    Timestamp due;
    String title,subject,content;
    List<String> links;

    public Assignment(Timestamp due, String title, String subject, String content, List<String> links) {
        this.due = due;
        this.title = title;
        this.subject = subject;
        this.content = content;
        this.links = links;
    }

    public Timestamp getDue() {
        return due;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public List<String> getLinks() {
        return links;
    }
}
