package exercise.repository;

import exercise.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN

    List<Product> findByPriceBetweenOrderByPrice(Integer startPrice, Integer endPrice, PageRequest pageRequest);
    // END
}
