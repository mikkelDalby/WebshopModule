package dk.webshopmodule.model;

import javax.persistence.*;

@Entity
@Table(name = "orderlines")
public class OrderLine {
    @Id
    @Column(name = "l_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "p_fk")
    private Product product;

    @Column(name = "p_price")
    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "o_id")
    private Order order;

    @Column(name = "amount")
    private int quantity;

    public OrderLine() {
    }

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderLine(int id, Product product, double productPrice, Order order, int quantity) {
        this.id = id;
        this.product = product;
        this.productPrice = productPrice;
        this.order = order;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}