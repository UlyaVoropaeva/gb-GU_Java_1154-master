package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ROLES")
@Data
public class UsersRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_roleId", allocationSize = 1)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role", unique = true, columnDefinition = "VARCHAR", length = 50)
    private String role;

}
