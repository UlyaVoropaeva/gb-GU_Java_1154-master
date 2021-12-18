package ru.gb.mall.inventory.entity;


import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.PasswordType;
import io.quarkus.security.jpa.Username;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Access")
@Data
public class UsersAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_productId", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR", length = 100)
    @Username
    private String email;

    @Column(name = "password", nullable = false )
    @Password(value = PasswordType.CUSTOM)
    private Password password;
}
