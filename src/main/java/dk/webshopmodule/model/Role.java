package dk.webshopmodule.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private int r_id;
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public Role(int r_id, String role, List<User> users) {
        this.r_id = r_id;
        this.role = role;
        this.users = users;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
