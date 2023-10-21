package org.trials.kafka.kafkasample.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Trade implements Serializable {

    private Long id;

    private BigDecimal price;

    private BigDecimal quantity;

    private String symbol;

    public Trade() {
    }

    public Trade(Long id, BigDecimal price, BigDecimal quantity, String symbol) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
