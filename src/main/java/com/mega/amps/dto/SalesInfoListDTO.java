package com.mega.amps.dto;

public class SalesInfoListDTO {

    private Long sales_info_list_id;
    private String sales_id;
    private String product_barcode;
    private String selling_price;
    private String selling_quantity;
    private String total_price;
    private String total_cost_price;
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

    @Override
    public String toString() {
        return "SalesInfoListDTO{" +
                "sales_info_list_id=" + sales_info_list_id +
                ", sales_id='" + sales_id + '\'' +
                ", product_barcode='" + product_barcode + '\'' +
                ", selling_price='" + selling_price + '\'' +
                ", selling_quantity='" + selling_quantity + '\'' +
                ", total_price='" + total_price + '\'' +
                ", total_cost_price='" + total_cost_price + '\'' +
                ", total_profit_loss='" + total_profit_loss + '\'' +
                '}';
    }
}
