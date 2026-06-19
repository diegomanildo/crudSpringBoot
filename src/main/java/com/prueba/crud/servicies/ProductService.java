package com.prueba.crud.servicies;

import com.prueba.crud.entities.ProductModel;
import com.prueba.crud.exceptions.ResourceNotFoundException;
import com.prueba.crud.mappers.ProductMapper;
import com.prueba.crud.product.ProductRequest;
import com.prueba.crud.product.ProductResponse;
import com.prueba.crud.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getProducts() {
        return productMapper.toResponseList(productRepository.findAll());
    }

    public ProductResponse saveProduct(ProductRequest request) {
        ProductModel product = new ProductModel();
        product.setName(request.name());
        product.setQuantity(request.quantity());
        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    public ProductResponse updateProductById(ProductRequest request, Long id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        product.setName(request.name());
        product.setQuantity(request.quantity());
        return productMapper.toResponse(productRepository.save(product));
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
