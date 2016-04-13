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
    String title;

    @Column(name = "text")
    String text;

    @Column(name = "coment")
    String comment;

    public News(String title, String text, String comment) {
        this.title = title;
        this.text = text;
        this.comment = comment;
    }

    public News() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title_news) {
        this.title = title_news;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
