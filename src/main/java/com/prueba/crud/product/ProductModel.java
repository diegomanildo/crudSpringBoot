package com.prueba.crud.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column <- no hace falta pero estaria bueno
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private int quantity;
}
