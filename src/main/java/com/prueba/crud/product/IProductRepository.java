package com.prueba.crud.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {

}
