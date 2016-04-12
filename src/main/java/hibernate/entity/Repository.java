package hibernate.entity;

import javax.persistence.*;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Entity
@Table(name = "repository")
public class Repository {

    @Id
    @Column(name = "adress")
    Integer adress;

    @ManyToOne
    @JoinColumn(name = "title_news")
    News title_news;

    @ManyToOne
    @JoinColumn(name = "login_adm")

    Admin login_adm;

    @ManyToOne
    @JoinColumn(name = "login_user")
    User login_user;

    public Repository(Integer adress, User login_user, Admin login_adm, News title_news) {
        this.adress = adress;
        this.login_user = login_user;
        this.login_adm = login_adm;
        this.title_news = title_news;
    }

    public Repository() {
    }

    public Integer getAdress() {
        return adress;
    }

    public void setAdress(Integer adress) {
        this.adress = adress;
    }

    public News getTitle_news() {
        return title_news;
    }

    public void setTitle_news(News title_news) {
        this.title_news = title_news;
    }

    public Admin getLogin_adm() {
        return login_adm;
    }

    public void setLogin_adm(Admin login_adm) {
        this.login_adm = login_adm;
    }

    public User getLogin_user() {
        return login_user;
    }

    public void setLogin_user(User login_user) {
        this.login_user = login_user;
    }
}
