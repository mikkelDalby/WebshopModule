package dk.webshopmodule.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private int c_id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category() {
    }

    public Category(int c_id, String name, List<Product> products) {
        this.c_id = c_id;
        this.name = name;
        this.products = products;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
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
                "c_id=" + c_id +
                ", name='" + name +
                '}';
    }
}