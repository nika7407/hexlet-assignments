package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductParamsDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.specification.ProductSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSpecification specBuilder;

    // BEGIN

    @GetMapping("")
    public List<ProductDTO> get(
            ProductParamsDTO paramsDTO,
            @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);

        Specification<Product> specs = specBuilder.filter(paramsDTO);

        Page<Product> productsPage = productRepository.findAll(specs, pageable);

        return productsPage.map(productMapper::map).toList();
    }
    // END

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@Valid @RequestBody ProductCreateDTO productData) {
        var product = productMapper.map(productData);
        productRepository.save(product);
        var productDto = productMapper.map(product);
        return productDto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found: " + id));
        var productDto = productMapper.map(product);
        return productDto;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody @Valid ProductUpdateDTO productData, @PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found: " + id));

        productMapper.update(productData, product);
        productRepository.save(product);
        var productDto = productMapper.map(product);
        return productDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
