package darian.saric.nmbp.model;

import javax.print.attribute.standard.DateTimeAtCreation;

public class Comment {
    //todo prilagoditi razred za baratanje u mongu
    private String id;
    private String text;
    private DateTimeAtCreation dateTimeAtCreation;
    private String newsId;

    public Comment(String id, String text, DateTimeAtCreation dateTimeAtCreation, String newsId) {
        this.id = id;
        this.text = text;
        this.dateTimeAtCreation = dateTimeAtCreation;
        this.newsId = newsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTimeAtCreation getDateTimeAtCreation() {
        return dateTimeAtCreation;
    }

    public void setDateTimeAtCreation(DateTimeAtCreation dateTimeAtCreation) {
        this.dateTimeAtCreation = dateTimeAtCreation;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
