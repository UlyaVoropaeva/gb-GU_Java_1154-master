package ru.gb.mall.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "USERS")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_usersId", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active", nullable = false)
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "USERS_ROLE_RELATION",
            joinColumns = @JoinColumn(
                    name = "USER_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_ROLE_USERS_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION ON UPDATE NO ACTION"
                    )

            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_USERS_ROLE_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE NO ACTION ON UPDATE NO ACTION"
                    )

            )
    )
    private Collection<UsersRole> roles;

    @OneToOne
    @JoinColumn(
            name = "user_userAccess_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_USER_ACCESS_ID_RELATION")
    )
    private UsersAccess usersAccess;
}