package com.bulletinBoard.system.bl.dto;

import com.bulletinBoard.system.persistance.entity.User;

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
    
    public UserDTO(User user) {
        super();
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getAddress();
    }
}
