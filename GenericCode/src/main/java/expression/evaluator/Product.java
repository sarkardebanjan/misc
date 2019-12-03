package expression.evaluator;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String category;

    private Integer status;

    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String category, Integer status, BigDecimal price) {
        this.id = id;
        this.category = category;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Integer getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
