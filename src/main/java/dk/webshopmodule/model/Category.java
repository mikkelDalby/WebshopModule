package dk.webshopmodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "c_id")
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category() {
    }

    public Category(int id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}