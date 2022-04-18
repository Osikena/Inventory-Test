package com.mega.amps.service;

import com.mega.amps.domain.Product;
import com.mega.amps.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public ProductService(){ }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product getById(Long id) { return  productRepository.getById(id); }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product saveAndFlush(Product product){
        return productRepository.saveAndFlush(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product findProductByBarcode(String barcode){ return productRepository.findProductByBarcode(barcode); }

    public List<String> findAllCategory() { return productRepository.findAllCategory(); }

    public List<Product> findAllProductByCategory(String category){
        return productRepository.findAllProductByCategory(category);
    }

    public List<String> findAllProductQuantityByCategory(String category){
        return productRepository.findAllProductQuantityByCategory(category);
    }

    public List<String> findAllProductInStockByCategory(String category){
        return productRepository.findAllProductInStockByCategory(category);
    }

//    @Transactional
//    public void deleteProductByBarcode(String barcode){
//        productRepository.deleteProductByBarcode(barcode);
//    }

}
