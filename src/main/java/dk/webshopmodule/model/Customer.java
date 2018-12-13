package dk.webshopmodule.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "c_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company")
    private String company;

    @Column(name = "i_adress")
    private String iAdress;

    @Column(name = "i_zipcode")
    private String iZipcode;

    @Column(name = "i_town")
    private String iTown;

    @Column(name = "i_country")
    private String iCountry;

    @Column(name = "d_adress")
    private String dAdress;

    @Column(name = "d_zipcode")
    private String dZipcode;

    @Column(name = "d_town")
    private String dTown;

    @Column(name = "d_country")
    private String dCountry;

    @OneToMany(mappedBy = "delivery")
    private List<Order> orders;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String email, String phone, String company, String iAdress, String iZipcode, String iTown, String iCountry, String dAdress, String dZipcode, String dTown, String dCountry, List<Order> orders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.iAdress = iAdress;
        this.iZipcode = iZipcode;
        this.iTown = iTown;
        this.iCountry = iCountry;
        this.dAdress = dAdress;
        this.dZipcode = dZipcode;
        this.dTown = dTown;
        this.dCountry = dCountry;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getiAdress() {
        return iAdress;
    }

    public void setiAdress(String iAdress) {
        this.iAdress = iAdress;
    }

    public String getiZipcode() {
        return iZipcode;
    }

    public void setiZipcode(String iZipcode) {
        this.iZipcode = iZipcode;
    }

    public String getiTown() {
        return iTown;
    }

    public void setiTown(String iTown) {
        this.iTown = iTown;
    }

    public String getiCountry() {
        return iCountry;
    }

    public void setiCountry(String iCountry) {
        this.iCountry = iCountry;
    }

    public String getdAdress() {
        return dAdress;
    }

    public void setdAdress(String dAdress) {
        this.dAdress = dAdress;
    }

    public String getdZipcode() {
        return dZipcode;
    }

    public void setdZipcode(String dZipcode) {
        this.dZipcode = dZipcode;
    }

    public String getdTown() {
        return dTown;
    }

    public void setdTown(String dTown) {
        this.dTown = dTown;
    }

    public String getdCountry() {
        return dCountry;
    }

    public void setdCountry(String dCountry) {
        this.dCountry = dCountry;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                ", iAdress='" + iAdress + '\'' +
                ", iZipcode='" + iZipcode + '\'' +
                ", iTown='" + iTown + '\'' +
                ", iCountry='" + iCountry + '\'' +
                ", dAdress='" + dAdress + '\'' +
                ", dZipcode='" + dZipcode + '\'' +
                ", dTown='" + dTown + '\'' +
                ", dCountry='" + dCountry + '\'' +
                '}';
    }
}