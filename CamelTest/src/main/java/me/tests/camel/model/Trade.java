package me.tests.camel.model;

public class Trade {

    private long id;

    private double price;

    private double quantity;

    private String broker;

    public Trade(long id, double price, double quantity, String broker) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.broker = broker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", broker='" + broker + '\'' +
                '}';
    }
}
