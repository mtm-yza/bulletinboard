package com.bulletinBoard.system.api.response;

import com.bulletinBoard.system.bl.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PostResponse Class</h2>
 * <p>
 * Process for Displaying PostResponse
 * </p>
 * 
 * @author YeZawAung
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private int id;

    /**
     * <h2>title</h2>
     * <p>
     * title
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
     * isActive
     * </p>
     */
    private Boolean isActive;

    /**
     * <h2>user</h2>
     * <p>
     * User Response
     * </p>
     */
    private UserResponse user;

    /**
     * <h2>Constructor for PostResponse</h2>
     * <p>
     * Constructor for PostResponse
     * </p>
     * 
     * @param post PostDTO
     */
    public PostResponse(PostDTO post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.isActive = post.getIsActive();
        this.description = post.getDescription();
        this.user = new UserResponse(post.getUser());
    }
}
