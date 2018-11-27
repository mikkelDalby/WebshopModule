package dk.webshopmodule.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pc")
public class PC {
    @Id
    private String p_fk;
    @Id
    private int c_fk;

    public PC() {
    }

    public PC(String p_fk, int c_fk) {
        this.p_fk = p_fk;
        this.c_fk = c_fk;
    }

    public String getP_fk() {
        return p_fk;
    }

    public void setP_fk(String p_fk) {
        this.p_fk = p_fk;
    }

    public int getC_fk() {
        return c_fk;
    }

    public void setC_fk(int c_fk) {
        this.c_fk = c_fk;
    }
}