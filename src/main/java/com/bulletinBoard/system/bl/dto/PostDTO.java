package com.bulletinBoard.system.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private int status;
}
