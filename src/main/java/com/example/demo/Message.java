package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    private String title;

    @NotNull
    @Size(min=4)
    private String content;

    @NotNull
    @Size(min=4)
    private String postedDate;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
//    public Date posteddate;

    @NotNull
    @Size(min=3)
    private String postedBy;

//    public Message(@NotNull @Size(min = 4) String title,
//                   @NotNull @Size(min = 3) String content,
//                   @NotNull @Size(min = 3) String postedDate,
//                   @NotNull @Size(min = 3) String postedBy) {
//        this.title = title;
//        this.content = content;
//        this.postedDate = postedDate;
//        this.postedBy = postedBy;
//    }

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

//    public Date getPosteddate() {
//        return posteddate;
//    }
//
//    public void setPosteddate(Date posteddate) {
//        this.posteddate = posteddate;
//    }
}
