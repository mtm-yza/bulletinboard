package com.bulletinBoard.system.web.form;

import javax.validation.constraints.NotBlank;

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

    private int id;

    @NotBlank(message = "Title is Required")
    private String title;

    @NotBlank(message = "Description is Required")
    private String description;

    private int status;

    public PostForm(String title, String description, int status) {
        super();
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
