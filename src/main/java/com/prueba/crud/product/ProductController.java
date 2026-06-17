package com.prueba.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ArrayList<ProductModel> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody ProductModel product) {
        return productService.saveProduct(product);
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductModel> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(path = "/{id}")
    public ProductModel updateProductById(@RequestBody ProductModel request, @PathVariable("id") Long id) {
        return productService.updateProductById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        boolean ok = productService.deleteProductById(id);
        if (ok) {
            return "Product with id " + id + " deleted";
        } else {
            return "ERROR: Product with id " + id + " could not be deleted";
        }
    }
}
