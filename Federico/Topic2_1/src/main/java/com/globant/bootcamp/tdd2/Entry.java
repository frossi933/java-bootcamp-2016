package com.globant.bootcamp.tdd2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Entry {
    private String title;
    private String body;
    private Date date;
    private String author;
    private LinkedList<String> comments;
    private double rating = 0.0;
    private int votes = 0;
    private final double MIN_RATE = 0.0;
    private final double MAX_RATE = 5.0;


    public Entry(String title, String author){
        this.title = title;
        this.author = author;
        comments = new LinkedList<String>();
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void rate(double value){
        if(value >= MIN_RATE && value <= MAX_RATE){
            rating = ((rating*votes)+value)/(votes + 1);
            votes++;
        }
    }

    public void addComment(String comment){
        comments.add(comment);
    }
}
