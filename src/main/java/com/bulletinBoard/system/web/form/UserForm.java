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
public class UserForm {

    private int id;
    @NotBlank(message = "Name is Required")
    private String name;
    @NotBlank(message = "Email is Required")
    private String email;
    @NotBlank(message = "Address is Required")
    private String address;
    private String password;

    public UserForm(String name, String email, String address, String password) {
        super();
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
    }
    
    public UserForm(int id, String name, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
