package dk.webshopmodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "p_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "purchase_price")
    private Double purchasePrice;
    @Column(name = "sales_price")
    private Double salesPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "desription")
    private String description;
    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany
    @JoinTable(
            name = "pc",
            joinColumns = @JoinColumn(name = "p_fk", referencedColumnName = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "c_fk", referencedColumnName = "c_id"))
    private List<Category> categories;

    public Product() {
    }

    public Product(String id, String name, Double purchasePrice, Double salesPrice, int quantity, String barcode, String description, String imagePath, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.barcode = barcode;
        this.description = description;
        this.imagePath = imagePath;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salesPrice=" + salesPrice +
                ", quantity=" + quantity +
                ", barcode='" + barcode + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}