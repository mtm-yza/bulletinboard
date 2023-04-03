package com.bulletinBoard.system.web.form;

import javax.validation.constraints.NotBlank;

import com.bulletinBoard.system.persistance.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostForm {

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
    @NotBlank(message = "Title is Required")
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * Description Of Post
     * </p>
     */
    @NotBlank(message = "Description is Required")
    private String description;

    /**
     * <h2>isActiveStatus</h2>
     * <p>
     * Value that indicate if the post is active.
     * </p>
     */
    private Boolean isActive;

    /**
     * <h2>Constructor for PostForm</h2>
     * <p>
     * Constructor for PostForm
     * </p>
     * 
     * @param post Posts
     */
    public PostForm(Post post) {
        super();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.isActive = post.getIsActive();
    }
}
