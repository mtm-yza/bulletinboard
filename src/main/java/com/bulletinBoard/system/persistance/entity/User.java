package com.bulletinBoard.system.persistance.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.bulletinBoard.system.web.form.UserForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h2>User</h2>
 * <p>
 * User Entity
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    /**
     * <h2>id</h2>
     * <p>
     * User's ID
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * <h2>name</h2>
     * <p>
     * User's Name
     * </p>
     */
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * User's Email
     * </p>
     */
    @Column(unique = true)
    private String email;

    /**
     * <h2>address</h2>
     * <p>
     * User's Address
     * </p>
     */
    private String address;

    /**
     * <h2>password</h2>
     * <p>
     * User's Password
     * </p>
     */
    private String password;

    /**
     * <h2>created_at</h2>
     * <p>
     * Created Date
     * </p>
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;

    /**
     * <h2>updated_at</h2>
     * <p>
     * Updated Date
     * </p>
     */
    @UpdateTimestamp
    private Timestamp updated_at;

    /**
     * <h2>User's Authorities</h2>
     * <p>
     * authorities
     * </p>
     */
    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Authority authority;

    /**
     * <h2>posts</h2>
     * <p>
     * posts
     * </p>
     */
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "user")
    private List<Post> posts;

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param name      String
     * @param email     email
     * @param address   String
     * @param password  String
     * @param authority Authority
     */
    public User(String name, String email, String address, String password, Authority authority) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.authority = authority;
    }

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param user UserForm
     */
    public User(UserForm user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getPassword();
    }
}
