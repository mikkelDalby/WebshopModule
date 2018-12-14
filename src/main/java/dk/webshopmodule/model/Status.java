package dk.webshopmodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "s_id")
    private int id;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;

    public Status() {
    }

    public Status(int id) {
        this.id = id;
    }

    public Status(int id, String status, List<Order> orders) {
        this.id = id;
        this.status = status;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
