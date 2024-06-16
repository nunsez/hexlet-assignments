package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public final class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
            .and(withTitleCont(params.getTitleCont()))
            .and(withPriceLt(params.getPriceLt()))
            .and(withPriceGt(params.getPriceGt()))
            .and(withRatingGt(params.getRatingGt()));
    }

    private static Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return cb.conjunction();
            return cb.equal(root.get("category").get("id"), categoryId);
        };
    }

    private static Specification<Product> withTitleCont(String title) {
        return (root, query, cb) -> {
            if (title == null) return cb.conjunction();
            return cb.like(root.get("title"), title);
        };
    }

    private static Specification<Product> withPriceLt(Integer price) {
        return (root, query, cb) -> {
            if (price == null) return cb.conjunction();
            return cb.lt(root.get("price"), price);
        };
    }

    private static Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) -> {
            if (price == null) return cb.conjunction();
            return cb.gt(root.get("price"), price);
        };
    }

    private static Specification<Product> withRatingGt(Double rating) {
        return (root, query, cb) -> {
            if (rating == null) return cb.conjunction();
            return cb.gt(root.get("rating"), rating);
        };
    }

}
// END
