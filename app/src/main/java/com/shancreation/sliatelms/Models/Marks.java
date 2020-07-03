package com.shancreation.sliatelms.Models;

public class Marks {
    String Title,Subject,Due,owner,postDate;
    int marks;

    public Marks(String title, String subject, String due, String owner, String postDate, int marks) {
        Title = title;
        Subject = subject;
        Due = due;
        this.owner = owner;
        this.postDate = postDate;
        this.marks = marks;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubject() {
        return Subject;
    }

    public String getDue() {
        return Due;
    }

    public String getOwner() {
        return owner;
    }

    public String getPostDate() {
        return postDate;
    }

    public int getMarks() {
        return marks;
    }
}
