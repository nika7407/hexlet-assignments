package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> find(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {

        // Set default price range if not provided
        int minPrice = min != null ? min : 0;
        int maxPrice = max != null ? max : Integer.MAX_VALUE;

        // Set default page size based on whether page parameter is provided
        int pageSize = page != null ? 5 : 30;
        int pageNumber = page != null ? page - 1 : 0; // Convert to 0-based index

        // Get paginated results
        List<Product> pagedResults = productRepository.findByPriceBetweenOrderByPrice(
                minPrice,
                maxPrice,
                PageRequest.of(pageNumber, pageSize)
        );

        return pagedResults;
    }

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
