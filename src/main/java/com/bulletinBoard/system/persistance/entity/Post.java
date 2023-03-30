package com.bulletinBoard.system.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bulletinBoard.system.web.form.PostForm;

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

    /**
     * <h2>id</h2>
     * <p>
     * ID Number Of Post
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * <h2>title</h2>
     * <p>
     * Title Of Post
     * </p>
     */
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    private String description;

    /**
     * <h2>isActive</h2>
     * <p>
     * Status if the status is active
     * </p>
     */
    private Boolean isActive;

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param title       String
     * @param description String
     * @param isActive    Boolean
     */
    public Post(String title, String description, Boolean isActive) {
        super();
        this.title = title;
        this.description = description;
        this.isActive = isActive;
    }

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param post PostForm
     */
    public Post(PostForm post) {
        super();
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.isActive = post.getIsActive();
    }
}
