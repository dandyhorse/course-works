package hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vladimir on 04.04.2016.
 */
@Entity
@Table(name = "news")
public class News {

    @Id
    @Column(name = "title_news")
    String title_news;

    @Column(name = "text")
    String text;

    @Column(name = "coment")
    String coment;

    public News(String title_news, String text, String coment) {
        this.title_news = title_news;
        this.text = text;
        this.coment = coment;
    }

    public News() {
    }

    public String getTitle_news() {
        return this.title_news;
    }

    public void setTitle_news(String title_news) {
        this.title_news = title_news;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComent() {
        return this.coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
