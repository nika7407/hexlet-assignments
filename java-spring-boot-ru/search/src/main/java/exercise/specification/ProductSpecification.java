package exercise.specification;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> filter(ProductParamsDTO para) {
        return rightCategory(para.getCategoryId())
                .and(moreThan(para.getPriceGt()))
                .and(lessThan(para.getPriceLt()))
                .and(moreThanRating(para.getRatingGt()))
                .and(name(para.getTitleCont()));
    }
    //  Товар находится в определенной категории

    private Specification<Product> rightCategory(Long categoryId) {
        return (root, query, cb) -> {

            if (categoryId == null) {
                return cb.conjunction();
            } else {
                return cb.equal(root.get("category").get("id"), categoryId);
            }
        };
    }

    private Specification<Product> moreThan(Integer priceGt) {
        return (root, query, cb) ->
                priceGt == null ? cb.conjunction()
                        : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> lessThan(Integer priceLt) {
        return (root, query, cb) ->
                priceLt == null ? cb.conjunction()
                        : cb.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> moreThanRating(Double RatingGt) {
        return (root, query, cb) ->
                RatingGt == null ? cb.conjunction()
                        : cb.greaterThan(root.get("rating"), RatingGt);
    }

    private Specification<Product> name(String titleCont) {
        return (rt, query, cb) ->
                titleCont == null ? cb.conjunction()
                        : cb.like(cb.lower(rt.get("title")), "%" + titleCont.toLowerCase() + "%");
    }


}
// END
