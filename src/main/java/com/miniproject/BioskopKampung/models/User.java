package com.miniproject.BioskopKampung.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "[Password]")
    private String password;

    @Column(name = "[Role]")
    private String role;

    @Column(name = "IsEnabled")
    private boolean isEnabled;

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = "User";
    }
}
