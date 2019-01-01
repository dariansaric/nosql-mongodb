package darian.saric.nmbp.model;

import com.mongodb.gridfs.GridFSFile;

import javax.print.attribute.standard.DateTimeAtCreation;

public class Vijest {
    //TODO prilagoditi razred za baratanje u mongu
    private String id;
    private String author;
    private String text;
    private GridFSFile picture;
    private DateTimeAtCreation dateTimeAtCreation;


    public Vijest(String id, String author, String text, GridFSFile picture, DateTimeAtCreation dateTimeAtCreation) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.picture = picture;
        this.dateTimeAtCreation = dateTimeAtCreation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GridFSFile getPicture() {
        return picture;
    }

    public void setPicture(GridFSFile picture) {
        this.picture = picture;
    }

    public DateTimeAtCreation getDateTimeAtCreation() {
        return dateTimeAtCreation;
    }

    public void setDateTimeAtCreation(DateTimeAtCreation dateTimeAtCreation) {
        this.dateTimeAtCreation = dateTimeAtCreation;
    }
}
