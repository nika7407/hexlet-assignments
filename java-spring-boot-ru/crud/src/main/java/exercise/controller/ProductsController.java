package exercise.controller;

import java.util.ArrayList;
import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    // BEGIN
    @GetMapping("")
    public List<ProductDTO> getList(){
        List<ProductDTO> list = new ArrayList<>();
         productRepository.findAll().forEach(product -> list.add(productMapper.map(product)));
         return list;
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString()));
        return productMapper.map(product);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)

    public void create(@RequestBody ProductCreateDTO create){
       var product = productMapper.map(create);

        if (!categoryRepository.existsById(product.getCategory().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }
       productRepository.save(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductUpdateDTO dto){
        var product = productRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("NOTFOUND"));

        if (!categoryRepository.existsById(product.getCategory().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category ID");
        }

        productMapper.update(dto,product);
        productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){

        var product = productRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("NOTFOUND"));

        productRepository.delete(product);

    }

    // END
}
