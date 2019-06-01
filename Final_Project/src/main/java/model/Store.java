package model;

public class Store implements IModel{
    public int id;
    public String name;
    public int sellerId;

    public Store() {
    }

    public Store(int id, String name, int sellerId) {
        this.id = id;
        this.name = name;
        this.sellerId = sellerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
