package com.prueba.crud.repositories;

import com.prueba.crud.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

}
