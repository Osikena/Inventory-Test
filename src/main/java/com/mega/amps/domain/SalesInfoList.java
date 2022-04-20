package com.mega.amps.domain;

import javax.persistence.*;

@Entity
@Table(name = "sales_info_list")
public class SalesInfoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_info_list_id", length = 100)
    private Long sales_info_list_id;

    @Column(name = "sales_id", length = 100)
    private String sales_id;

    @Column(name = "product_barcode", length = 100)
    private String product_barcode;

    @Column(name = "selling_price", length = 100)
    private String selling_price;

    @Column(name = "selling_quantity", length = 100)
    private String selling_quantity;

    @Column(name = "total_price", length = 100)
    private String total_price;

    @Column(name = "total_cost_price", length = 100)
    private String total_cost_price;

    @Column(name = "total_profit_loss", length = 100)
    private String total_profit_loss;

    public Long getSales_info_list_id() {
        return sales_info_list_id;
    }

    public void setSales_info_list_id(Long sales_info_list_id) {
        this.sales_info_list_id = sales_info_list_id;
    }

    public String getSales_id() {
        return sales_id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getSelling_quantity() {
        return selling_quantity;
    }

    public void setSelling_quantity(String selling_quantity) {
        this.selling_quantity = selling_quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getTotal_cost_price() {
        return total_cost_price;
    }

    public void setTotal_cost_price(String total_cost_price) {
        this.total_cost_price = total_cost_price;
    }

    public String getTotal_profit_loss() {
        return total_profit_loss;
    }

    public void setTotal_profit_loss(String total_profit_loss) {
        this.total_profit_loss = total_profit_loss;
    }
}
