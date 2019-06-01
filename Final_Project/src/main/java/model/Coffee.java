package model;

public class Coffee implements IModel {
    public int id;
    public String title;
    public String description;
    public String url;
    public int price;

    public Coffee() {
    }

    public Coffee(int id, String title, String description, String url, int price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
