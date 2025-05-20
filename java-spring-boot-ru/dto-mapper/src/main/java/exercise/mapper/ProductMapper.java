package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import javax.xml.transform.Source;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface  ProductMapper {

    @Mapping(source = "vendorCode", target = "barcode")
    @Mapping(source = "title", target = "name")
    @Mapping(target = "cost", source = "price")
    public  Product map(ProductCreateDTO dto);

    @Mapping(source = "vendorCode", target = "barcode")
    @Mapping(source = "title", target = "name")
    public  Product map(ProductDTO dto);

    @Mapping(source = "barcode", target = "vendorCode")
    @Mapping(source = "name", target = "title")
    @Mapping(source = "cost", target = "price")
    public  ProductDTO map(Product model);

    @Mapping(target = "cost", source = "price")
    public void update(ProductUpdateDTO dto, @MappingTarget Product model);
}
// END
