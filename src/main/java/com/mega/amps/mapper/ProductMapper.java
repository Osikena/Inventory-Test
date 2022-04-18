package com.mega.amps.mapper;

import com.mega.amps.domain.Product;
import com.mega.amps.dto.ProductCategoryDTO;
import com.mega.amps.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper {

    @Mapping(source = "product_barcode", target = "product_barcode")
    @Mapping(source = "product_name", target = "product_name")
    Product toProduct(ProductDTO productDTO);

    List<ProductDTO> allProductDTO(List<Product> products);

    @Mapping(source = "product_id", target = "product_id")
    ProductDTO toProductDTO(Product product);
    
}
