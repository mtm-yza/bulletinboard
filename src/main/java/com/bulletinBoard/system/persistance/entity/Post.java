package com.bulletinBoard.system.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private int status;

    public Post(String title, String description, int status) {
        super();  
        this.title = title;
        this.description = description;
        this.status = status;
    }
    
    public Post(Post post) {
        super();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.status = post.getStatus();
    }
}
