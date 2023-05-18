package com.bulletinBoard.system.bl.dto;

import com.bulletinBoard.system.persistance.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorityDTO {

    /**
     * <h2>id</h2>
     * <p>
     * Authority ID
     * </p>
     */
    private Integer id;

    /**
     * <h2>name</h2>
     * <p>
     * Name of Authority
     * </p>
     */
    private String name;

    /**
     * <h2>Constructor for AuthorityDTO</h2>
     * <p>
     * Constructor for AuthorityDTO
     * </p>
     * 
     * @param authority Authority
     */
    public AuthorityDTO(Authority authority) {
        this.id = authority.getId();
        this.name = authority.getName();
    }
}
