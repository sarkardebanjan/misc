package deb.camel.transactions.sample.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private Timestamp orderDateTime;

    private BigDecimal orderAmount;

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Timestamp orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDateTime=" + orderDateTime +
                ", orderAmount=" + orderAmount +
                '}';
    }
}