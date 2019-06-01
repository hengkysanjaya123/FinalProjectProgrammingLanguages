package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Order implements IModel {
    private int id;
    private LocalDate orderDate;
    private int customerId;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int id, LocalDate orderDate, int customerId, List<OrderDetail> orderDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
