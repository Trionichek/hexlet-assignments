package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found."));
        return product;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @RequestBody Product product) {
        Product toChange = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found."));
        //toChange.setId(product.getId());
        toChange.setPrice(product.getPrice());
        toChange.setTitle(product.getTitle());
        return ResponseEntity.status(200).body(toChange);
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
