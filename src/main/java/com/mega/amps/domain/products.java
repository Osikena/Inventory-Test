package com.mega.amps.domain;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class products {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private long product_id;

    @Column(name = "product_name", length = 100)
    private String product_name;

    @Column(name = "product_category", length = 100)
    private String product_category;

    @Column(name = "recommended_selling_price", length = 100)
    private String recommended_selling_price;

    @Column(name = "quantity", length = 100)
    private String quantity;

    @Column(name = "cost_price", length = 100)
    private String cost_price;

    @Column(name = "date_time_created", length = 100)
    private String date_time_created;

    @Column(name = "production_date", length = 100)
    private String production_date;

    @Column(name = "expiration_date", length = 100)
    private String expiration_date;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getRecommended_selling_price() {
        return recommended_selling_price;
    }

    public void setRecommended_selling_price(String recommended_selling_price) {
        this.recommended_selling_price = recommended_selling_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(String date_time_created) {
        this.date_time_created = date_time_created;
    }

    public String getProduction_date() {
        return production_date;
    }

    public void setProduction_date(String production_date) {
        this.production_date = production_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}
