package com.mega.amps.dto;


public class SalesInfoConstDTO {

    private Long sales_info_const_id;
    private String sales_id;
    private String customer_name;
    private String seller_username;
    private String grand_total_price;
    private String grand_total_cost_price;
    private String grand_total_profit_loss;
    private String mode_of_payment;
    private String date_time_created;

    public Long getSales_info_const_id() {
        return sales_info_const_id;
    }

    public void setSales_info_const_id(Long sales_info_const_id) {
        this.sales_info_const_id = sales_info_const_id;
    }

    public String getSales_id() {
        return sales_id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSeller_username() {
        return seller_username;
    }

    public void setSeller_username(String seller_username) {
        this.seller_username = seller_username;
    }

    public String getGrand_total_price() {
        return grand_total_price;
    }

    public void setGrand_total_price(String grand_total_price) {
        this.grand_total_price = grand_total_price;
    }

    public String getGrand_total_cost_price() {
        return grand_total_cost_price;
    }

    public void setGrand_total_cost_price(String grand_total_cost_price) {
        this.grand_total_cost_price = grand_total_cost_price;
    }

    public String getGrand_total_profit_loss() {
        return grand_total_profit_loss;
    }

    public void setGrand_total_profit_loss(String grand_total_profit_loss) {
        this.grand_total_profit_loss = grand_total_profit_loss;
    }

    public String getMode_of_payment() {
        return mode_of_payment;
    }

    public void setMode_of_payment(String mode_of_payment) {
        this.mode_of_payment = mode_of_payment;
    }

    public String getDate_time_created() {
        return date_time_created;
    }

    public void setDate_time_created(String date_time_created) {
        this.date_time_created = date_time_created;
    }

    @Override
    public String toString() {
        return "SalesInfoConstDTO{" +
                "sales_info_const_id=" + sales_info_const_id +
                ", sales_id='" + sales_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", seller_username='" + seller_username + '\'' +
                ", grand_total_price='" + grand_total_price + '\'' +
                ", grand_total_cost_price='" + grand_total_cost_price + '\'' +
                ", grand_total_profit_loss='" + grand_total_profit_loss + '\'' +
                ", mode_of_payment='" + mode_of_payment + '\'' +
                ", date_time_created='" + date_time_created + '\'' +
                '}';
    }
}
