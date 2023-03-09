package com.bulletinBoard.system.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Posts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "Title")
    private String title;
    
    @Column(name = "Description")
    private String description;
    
    @Column(name = "Status")
    private int status;
            
    public Posts() {
        super();
    }

    public Posts(int id, String title, String description, int status) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Posts(String title, String description, int status) {
        super();
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return new java.lang.StringBuilder("[")
                .append("title=" + title)
                .append(", description=" + description)
                .append(", status=" + status)
                .append("]")
                .toString();
    }
}
