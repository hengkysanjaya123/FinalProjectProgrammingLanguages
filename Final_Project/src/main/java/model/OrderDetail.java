package model;

public class OrderDetail implements IModel {
    private int id;
    private int orderId;
    private int productId;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int productId, int qty) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
