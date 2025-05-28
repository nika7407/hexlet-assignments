package exercise.mapper;

import exercise.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;

// BEGIN
@Mapper(componentModel = "spring")
public interface ReferenceMapper {
    default Category map(Long id) {
        if (id == null) return null;
        var category = new Category();
        category.setId(id);
        return category;
    }
}
// END
