package com.prueba.crud.services;

import com.prueba.crud.models.ProductModel;
import com.prueba.crud.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;

    public ArrayList<ProductModel> getProducts() {
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel saveProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public Optional<ProductModel> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductModel updateProductById(ProductModel request, Long id) {
        ProductModel product = productRepository.findById(id).get();

        product.setName(request.getName());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    public boolean deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
