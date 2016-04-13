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
    Integer address;

    @ManyToOne
    @JoinColumn(name = "title_news")
    News news;

    @ManyToOne
    @JoinColumn(name = "login_adm")

    Admin admin;

    @ManyToOne
    @JoinColumn(name = "login_user")
    User user;

    public Repository(Integer address, User user, Admin admin, News news) {
        this.address = address;
        this.user = user;
        this.admin = admin;
        this.news = news;
    }

    public Repository() {
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
