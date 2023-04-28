package com.bulletinBoard.system.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>Authority Class</h2>
 * <p>
 * Process for Displaying Authority
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority {

    /**
     * <h2>id</h2>
     * <p>
     * Authority ID
     * </p>
     */
    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * <h2>name</h2>
     * <p>
     * Authority Name
     * </p>
     */
    @Column(unique = true)
    private String name;
}
