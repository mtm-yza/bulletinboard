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

    private int id;
    private String title;
    private String description;
    private int status;
}
