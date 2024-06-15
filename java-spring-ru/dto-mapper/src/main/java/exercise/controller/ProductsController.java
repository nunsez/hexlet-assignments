package exercise.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        return products.stream()
            .map(productMapper::map)
            .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(""));
        return productMapper.map(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO data) {
        var product = productMapper.map(data);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO data) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(""));
        productMapper.update(data, product);
        productRepository.save(product);
        return productMapper.map(product);
    }
    // END
}
