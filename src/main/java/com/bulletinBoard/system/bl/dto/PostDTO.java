package com.bulletinBoard.system.bl.dto;

import java.util.List;

import com.bulletinBoard.system.persistance.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>PostDTO Class</h2>
 * <p>
 * Process for PostDTO
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PostDTO {

    /**
     * <h2>id</h2>
     * <p>
     * ID Number Of Post
     * </p>
     */
    private int id;

    /**
     * <h2>title</h2>
     * <p>
     * Title of Post
     * </p>
     */
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * Description Of Post
     * </p>
     */
    private String description;

    /**
     * <h2>status</h2>
     * <p>
     * Status Of Post
     * </p>
     */
    private Boolean isActive;
    
    
    /**
     * <h2> imageNames</h2>
     * <p>
     * Image Names
     * </p>
     */
    private List<String> imageNames;

    /**
     * <h2>user</h2>
     * <p>
     * User of Post
     * </p>
     */
    private UserDTO user;

    /**
     * <h2>Constructor for PostDTO</h2>
     * <p>
     * Constructor for PostDTO
     * </p>
     * 
     * @param post Post
     */
    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.isActive = post.getIsActive();
        this.description = post.getDescription();
        this.imageNames = post.getImageNames();
        this.user = new UserDTO(post.getUser());
    }
}
