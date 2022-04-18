package com.mega.amps.repository;

import com.mega.amps.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    @Query("select product from Product product where product.product_barcode = ?1")
    Product findProductByBarcode(String barcode);

    @Query("select distinct product_category from Product product")
    List<String> findAllCategory();

    @Query("select product from Product product where product.product_category = ?1")
    List<Product> findAllProductByCategory(String category);

    @Query("select quantity from Product product where product.product_category = ?1")
    List<String> findAllProductQuantityByCategory(String category);

    @Query("select quantity from Product product where product.product_category = ?1 and product.quantity > 0")
    List<String> findAllProductInStockByCategory(String category);

//    @Transactional
//    @Modifying
//    @Query("delete from Product product where product.product_barcode = :?1")
//    void deleteProductByBarcode(String barcode);



}
