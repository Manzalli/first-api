package projects_first_API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects_first_API.dtos.ProductDTO;
import projects_first_API.dtos.ProductRequestDTO;
import projects_first_API.entities.Product;
import projects_first_API.services.ProductService;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping ("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductDTO> showAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return service.getProductById(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductDTO addedProducts(@RequestBody ProductRequestDTO p) {
        return service.addProduct(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> removeEqualIDs(@PathVariable Integer id) {
        Optional<ProductDTO> result = service.removeProduct(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateData(@PathVariable Integer id, @RequestBody ProductRequestDTO newProduct) {
        Optional<ProductDTO> result = service.updateProduct(newProduct, id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }
}    