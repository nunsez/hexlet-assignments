package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.metadata.InvalidMetadataException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        var result = products.stream().map(productMapper::map).toList();
        return result;
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found"));
        return productMapper.map(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO data) throws MissingRequestValueException {
        var product = productMapper.map(data);
        try {
            productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new MissingRequestValueException("");
        }
        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO data) {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found"));
        productMapper.update(data, product);

        try {
            productRepository.save(product);
        } catch (JpaSystemException ignore) {
            productRepository.save(product);
        }

        return productMapper.map(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    // END
}
