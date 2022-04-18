package com.mega.amps.dto;

public class ProductCategoryDTO {

    private String product_category;
    private String size;
    private String in_stock;
    private String out_of_stock;

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(String in_stock) {
        this.in_stock = in_stock;
    }

    public String getOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(String out_of_stock) {
        this.out_of_stock = out_of_stock;
    }

    @Override
    public String toString() {
        return "ProductCategoryDTO{" +
                "product_category='" + product_category + '\'' +
                ", size='" + size + '\'' +
                ", in_stock='" + in_stock + '\'' +
                ", out_of_stock='" + out_of_stock + '\'' +
                '}';
    }
}
