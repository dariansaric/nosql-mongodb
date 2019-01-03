package darian.saric.nmbp.model;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Comment {
    //todo prilagoditi razred za baratanje u mongu
    private static final String ID_FIELD = "_id";
    private static final String TEXT_FIELD = "text";
    private static final String DATE_FIELD = "createdAt";
    private ObjectId id;
    private String text;
    private Date createdAt;

    private Comment() {

    }
//    public Comment(String id, String text, DateTimeAtCreation dateTimeAtCreation, String newsId) {
//        this.id = id;
//        this.text = text;
//        this.dateTimeAtCreation = dateTimeAtCreation;
//        this.newsId = newsId;
//    }

    public static Comment toComment(DBObject dbObject) throws ClassCastException {
        Comment c = new Comment();
        c.setId((ObjectId) dbObject.get(ID_FIELD));
        c.setText((String) dbObject.get(TEXT_FIELD));
        c.setDate((Date) dbObject.get(DATE_FIELD));

        return c;
    }

    //todo ne znam oće li trebati
//    public DBObject toDBObject() {
//        return null;
//    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return createdAt;
    }

    public void setDate(Date createdAt) {
        this.createdAt = createdAt;
    }
}
