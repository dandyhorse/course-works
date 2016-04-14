package hibernate.entity;

import javax.persistence.*;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Entity
@Table(name = "repository")
public class Repository {

    @Id
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    News news;

    @ManyToOne
    @JoinColumn(name = "admin_login")
    Admin admin;

    @ManyToOne
    @JoinColumn(name = "uzer_login")
    User user;

    public Repository(Integer id, News news, Admin admin, User user) {
        this.id = id;
        this.news = news;
        this.admin = admin;
        this.user = user;
    }

    public Repository() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
