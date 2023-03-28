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
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String address;
    private String password;
    
    public UserDTO(int id, String name, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
