package com.mega.amps.web.rest;

import com.mega.amps.domain.Product;
import com.mega.amps.domain.logic.Logic;
import com.mega.amps.dto.ProductCategoryDTO;
import com.mega.amps.dto.ProductDTO;
import com.mega.amps.dto.response.GenericResponse;
import com.mega.amps.dto.response.ListResponse;
import com.mega.amps.mapper.ProductCategoryMapper;
import com.mega.amps.mapper.ProductMapper;
import com.mega.amps.service.ProductService;
import liquibase.pro.packaged.V;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductResource {

    private ProductService productService;
    private ProductMapper productMapper;
    private Logic logic;
    private ProductCategoryMapper productCategoryMapper;

    public ProductResource(ProductService productService, ProductMapper productMapper, Logic logic,
                           ProductCategoryMapper productCategoryMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.logic = logic;
        this.productCategoryMapper = productCategoryMapper;
    }

    @PostMapping("/product/addproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponse> addProduct(@Valid @RequestBody ProductDTO productDTO){

        String barcode = productDTO.getProduct_barcode();
        if (barcode == null || barcode.equals("")){
            barcode = logic.generateBarcode();
            productDTO.setProduct_barcode(barcode);
        }

        String datetime = productDTO.getDate_time_created();
        LocalDateTime date_time;
        if (datetime == null){
            date_time = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            datetime = date_time.format(dateTimeFormatter);
            productDTO.setDate_time_created(datetime);
        }

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Product added successfully");


        if (!logic.isMatchesDouble(productDTO.getRecommended_selling_price())){

            response.setCode("99");
            response.setMessage("Recommended Selling Price is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (!logic.isMatchesDouble(productDTO.getCost_price())){

            response.setCode("99");
            response.setMessage("Cost Price is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (!logic.isMatchesInteger(productDTO.getQuantity())){

            response.setCode("99");
            response.setMessage("Quantity is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate todayDate = LocalDate.now();
        LocalDate productionDate = LocalDate.parse(productDTO.getProduction_date(), formatter);
        LocalDate expirationDate = LocalDate.parse(productDTO.getExpiration_date(), formatter);
        boolean isEqual = productionDate.isEqual(todayDate) || expirationDate.isEqual(todayDate);

        if (isEqual){

            response.setCode("99");
            response.setMessage("Production / Expiration date cannot be today.");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        Product product = productMapper.toProduct(productDTO);
        productService.save(product);

        Product product1 = productService.findProductByBarcode(productDTO.getProduct_barcode());

        productDTO.setProduct_id(product1.getProduct_id());

        response.setData(productDTO);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/product/editproduct")
    public ResponseEntity<GenericResponse> editProduct(@Valid @RequestBody ProductDTO productDTO){

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Product edited successfully");

        String barcode = productDTO.getProduct_barcode();
        if (barcode == null || barcode.equals("")){

            response.setCode("99");
            response.setMessage("Barcode cannot be empty");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (!logic.isMatchesDouble(productDTO.getRecommended_selling_price())){

            response.setCode("99");
            response.setMessage("Recommended Selling Price is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (!logic.isMatchesDouble(productDTO.getCost_price())){

            response.setCode("99");
            response.setMessage("Cost Price is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        if (!logic.isMatchesInteger(productDTO.getQuantity())){

            response.setCode("99");
            response.setMessage("Quantity is invalid");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate todayDate = LocalDate.now();
        LocalDate productionDate = LocalDate.parse(productDTO.getProduction_date(), formatter);
        LocalDate expirationDate = LocalDate.parse(productDTO.getExpiration_date(), formatter);
        boolean isEqual = productionDate.isEqual(todayDate) || expirationDate.isEqual(todayDate);

        if (isEqual){

            response.setCode("99");
            response.setMessage("Production / Expiration date cannot be today.");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        Product product = productMapper.toProduct(productDTO);
        productService.save(product);

        response.setData(productDTO);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/product/allproducts")
    public ResponseEntity<ListResponse> getAllProducts(){

        ListResponse response = new ListResponse();
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOList = productMapper.allProductDTO(products);

        response.setCode("00");
        response.setMessage("Successful");
        response.setData(productDTOList);
        response.setSize(Integer.toString(productDTOList.size()));
        response.setMetadata(null);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/product/product/id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@Valid @PathVariable Long id){

        Product product = productService.getById(id);
        ProductDTO productDTO = productMapper.toProductDTO(product);

        return new ResponseEntity<>(productDTO, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/product/product/barcode/{barcode}")
    public ResponseEntity<ProductDTO> getProductByBarcode(@Valid @PathVariable String barcode){

        Product product = productService.findProductByBarcode(barcode);
        ProductDTO productDTO = productMapper.toProductDTO(product);

        return new ResponseEntity<>(productDTO, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/product/allcategory")
    public ResponseEntity<ListResponse> getAllCategory(){

        ListResponse response = new ListResponse();
        List<String> productCategory = productService.findAllCategory();

        response.setCode("00");
        response.setMessage("Successful");
        response.setData(productCategory);
        response.setSize(Integer.toString(productCategory.size()));
        response.setMetadata(null);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/product/allcategory/data")
    public ResponseEntity<ListResponse> getAllCategoryData(){

        ListResponse response = new ListResponse();
        List<String> productCategory = productService.findAllCategory();
        List<String> productCategorySize = null;
        List<String> size = new ArrayList<>();
        List<String> productInStock = null;
        List<String> instock = new ArrayList<>();
        for (String productcategory : productCategory){
            productCategorySize = productService.findAllProductQuantityByCategory(productcategory);
            productInStock = productService.findAllProductInStockByCategory(productcategory);
            size.add(Integer.toString(productCategorySize.size()));
            instock.add(Integer.toString(productInStock.size()));
        }
        List<ProductCategoryDTO> productCategoryDTO = productCategoryMapper.allProductCategoryDTO(productCategory,
                size, instock);

        response.setCode("00");
        response.setMessage("Successful");
        response.setData(productCategoryDTO);
        response.setSize(Integer.toString(productCategoryDTO.size()));
        response.setMetadata(null);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/product/category/{category}")
    public ResponseEntity<ListResponse> getAllProductByCategory(@Valid @PathVariable String category){

        ListResponse response = new ListResponse();
        List<Product> products = productService.findAllProductByCategory(category);
        List<ProductDTO> productDTOList = productMapper.allProductDTO(products);

        response.setCode("00");
        response.setMessage("Successful");
        response.setData(productDTOList);
        response.setSize(Integer.toString(productDTOList.size()));
        response.setMetadata(null);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping("/product/delete/id/{id}")
    public ResponseEntity<GenericResponse> deleteProductById(@Valid @PathVariable Long id){

        GenericResponse response = new GenericResponse();

        productService.deleteById(id);

        response.setCode("00");
        response.setMessage("Product with Id " + Long.toString(id) + " successfully deleted");
        response.setData(null);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

//    @DeleteMapping("/product/delete/barcode/{barcode}")
//    public ResponseEntity<GenericResponse> deleteProductByBarcode(@Valid @PathVariable String barcode){
//
//        GenericResponse response = new GenericResponse();
//
//        productService.deleteProductByBarcode(barcode);
//
//        response.setCode("00");
//        response.setMessage("Product with barcode " + barcode + " successfully deleted");
//        response.setData(null);
//
//        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
//    }

}
