package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSE_KEEPERS")
@Data
public class WarehouseKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", columnDefinition = "VARCHAR", length = 255)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true, columnDefinition = "VARCHAR", length = 100)
    private String email;

    @OneToOne
    @JoinColumn(name = "WAREHOUSE_ID", referencedColumnName = "ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_WAREHOUSEKEEPER_WAREHOUSE_ID_RELATION")
    )
    private Warehouse warehouse;
}