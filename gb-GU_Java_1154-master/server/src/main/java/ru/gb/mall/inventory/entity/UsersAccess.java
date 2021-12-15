package ru.gb.mall.inventory.entity;

import io.quarkus.security.jpa.Username;
import lombok.Data;
import org.wildfly.security.password.Password;

import javax.persistence.*;

@Entity
@Table(name = "Access")
@Data
public class UsersAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    @Username
    private String email;

    @Column(name = "password", nullable = false )
    private Password password;
}
