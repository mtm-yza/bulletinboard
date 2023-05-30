package com.bulletinBoard.system.persistance.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bulletinBoard.system.web.form.PostForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author YeZawAung
 *
 */
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
     * <h2>imageNames</h2>
     * <p>
     * Image Names
     * </p>
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imageNames;

    /**
     * <h2>created_at</h2>
     * <p>
     * Creation Date
     * </p>
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;

    /**
     * <h2>user</h2>
     * <p>
     * user
     * </p>
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * <h2>updated_at</h2>
     * <p>
     * Last Update Date
     * </p>
     */
    @UpdateTimestamp
    private Timestamp updated_at;

    public Post(String title, String description, boolean isActive, User user) {
        this.title = title;
        this.description = description;
        this.isActive = isActive;
        this.user = user;
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
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.isActive = post.getIsActive();
    }
}
