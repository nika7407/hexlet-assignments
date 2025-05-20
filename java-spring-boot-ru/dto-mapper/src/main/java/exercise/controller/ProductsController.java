package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
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
    @Autowired
    private ProductMapper mapper;

    // BEGIN
    @GetMapping("")
    public List<ProductDTO> get(){
        List<ProductDTO> list = new ArrayList<>();
        productRepository.findAll().forEach(product ->{
            var DTO = mapper.map(product);
            list.add(DTO);
        });
        return list;
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Long id){
       if (!productRepository.existsById(id)){
           throw new ResourceNotFoundException("Not FOund ;-(");
       }
        Product product = productRepository.getById(id);
            return mapper.map(product);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto){
        var create = mapper.map(dto);
        productRepository.save(create);
        return mapper.map(create);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductUpdateDTO updateDTO, @PathVariable Long id){
        if (!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Not FOund ;-(");
        }
        Product product = productRepository.getById(id);
        mapper.update(updateDTO, product);
        productRepository.save(product);

        return mapper.map(product);
    }
    // END
}
