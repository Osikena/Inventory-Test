package com.mega.amps.mapper;

import com.mega.amps.domain.Product;
import com.mega.amps.dto.ProductCategoryDTO;
import com.mega.amps.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryMapper {

    public ProductCategoryDTO toProductCategoryDTO(String product, String size, String instock, String outofstock){
        if (product == null) {
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setProduct_category(product);
        productCategoryDTO.setSize(size);
        productCategoryDTO.setIn_stock(instock);
        productCategoryDTO.setOut_of_stock(outofstock);

        return productCategoryDTO;
    };

    public ProductCategoryDTO toProductCategoryDTO(String product){
        if (product == null) {
            return null;
        }
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setProduct_category(product);

        return productCategoryDTO;
    };

    public List<ProductCategoryDTO> allProductCategoryDTO(List<String> productscategory, List<String> size, List<String> instock) {
        if ( productscategory == null ) {
            return null;
        }

        List<ProductCategoryDTO> list = new ArrayList<ProductCategoryDTO>( productscategory.size() );

        for ( int counter = 0; counter < productscategory.size(); counter++ ) {
            list.add( toProductCategoryDTO( productscategory.get(counter), size.get(counter), instock.get(counter),
                    Integer.toString(Integer.parseInt(size.get(counter)) - Integer.parseInt(instock.get(counter)))));
        }

        return list;
    }

    public List<ProductCategoryDTO> allProductCategoryDTO(List<String> productscategory) {
        if ( productscategory == null ) {
            return null;
        }

        List<ProductCategoryDTO> list = new ArrayList<ProductCategoryDTO>( productscategory.size() );

        for ( int counter = 0; counter < productscategory.size(); counter++ ) {
            list.add( toProductCategoryDTO( productscategory.get(counter)));
        }

        return list;
    }

}
