package darian.saric.nmbp.model;

import org.bson.Document;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //todo prilagoditi razred za baratanje u mongu
    private static final String ID_FIELD = "_id";
    private static final String TEXT_FIELD = "text";
    private static final String DATE_FIELD = "createdAt";
    private String text;
    private Date date;

    private Comment() {

    }

    public Comment(String text, Date date) {
        this.text = text;
        this.date = date;
    }
    public Comment(String text) {
        this(text, new Date());
    }

    public static Comment toComment(Document dbObject) throws ClassCastException {
        Comment c = new Comment();
        c.setText((String) dbObject.get(TEXT_FIELD));
        c.setDate((Date) dbObject.get(DATE_FIELD));

        return c;
    }

    //todo ne znam oće li trebati
//    public DBObject toDBObject() {
//        return null;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date createdAt) {
        this.date = createdAt;
    }

    @Override
    public String toString() {
        return date + ": " + text;
    }
}
