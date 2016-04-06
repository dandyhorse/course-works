package hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Entity
@Table(
        name = "admin"
)
public class Admin {
    @Id
    @Column(
            name = "login_adm"
    )
    String login_adm;
    @Column(
            name = "password"
    )
    Integer password;

    public Admin(Integer password, String login_adm) {
        this.password = password;
        this.login_adm = login_adm;
    }

    public Admin() {
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getLogin() {
        return login_adm;
    }

    public void setLogin(String login_adm) {
        this.login_adm = login_adm;
    }
}
