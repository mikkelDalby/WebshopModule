package dk.webshopmodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String p_id;
    private String name;
    private Double purchase_price;
    private Double sales_price;
    private int quantity;
    private String barcode;
    private String description;
    private String image_path;

    @ManyToMany
    @JoinTable(
            name = "pc",
            joinColumns = @JoinColumn(name = "p_fk", referencedColumnName = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "c_fk", referencedColumnName = "c_id"))
    private List<Category> categories;

    public Product() {
    }

    public Product(String p_id, String name, Double purchase_price, Double sales_price, int quantity, String barcode, String description, String image_path, List<Category> categories) {
        this.p_id = p_id;
        this.name = name;
        this.purchase_price = purchase_price;
        this.sales_price = sales_price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.description = description;
        this.image_path = image_path;
        this.categories = categories;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(Double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Double getSales_price() {
        return sales_price;
    }

    public void setSales_price(Double sales_price) {
        this.sales_price = sales_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {

        return "Product{" +
                "p_id='" + p_id + '\'' +
                ", name='" + name + '\'' +
                ", purchase_price=" + purchase_price +
                ", sales_price=" + sales_price +
                ", quantity=" + quantity +
                ", barcode='" + barcode + '\'' +
                ", description='" + description + '\'' +
                ", image_path='" + image_path + '\'' +
                '}';
    }
}