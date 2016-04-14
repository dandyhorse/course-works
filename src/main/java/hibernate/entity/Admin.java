package hibernate.entity;

import javax.persistence.*;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    public Admin(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public Admin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
