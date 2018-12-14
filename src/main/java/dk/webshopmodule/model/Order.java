package dk.webshopmodule.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "o_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "d_fk")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "c_fk")
    private Customer customer;

    @Column(name = "total_price")
    private double totalPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    @Column(name = "date_time")
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "s_fk")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "pay_fk")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Delivery delivery, Customer customer, double totalPrice, Date dateTime, Status status, Payment payment, List<OrderLine> orderLines) {
        this.delivery = delivery;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
        this.status = status;
        this.payment = payment;
        this.orderLines = orderLines;
    }

    public Order(int id, Delivery delivery, Customer customer, double totalPrice, Date dateTime, Status status, Payment payment, List<OrderLine> orderLines) {
        this.id = id;
        this.delivery = delivery;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
        this.status = status;
        this.payment = payment;
        this.orderLines = orderLines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", delivery=" + delivery +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", payment=" + payment +
                '}';
    }
}
