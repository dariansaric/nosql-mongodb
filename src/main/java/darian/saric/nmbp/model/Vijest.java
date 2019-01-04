package darian.saric.nmbp.model;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vijest {
    //TODO prilagoditi razred za baratanje u mongu
    public static final String ID_FIELD = "_id";
    public static final String TITLE_FIELD = "title";
    public static final String AUTHOR_FIELD = "author";
    public static final String TEXT_FIELD = "text";
    public static final String PICTURE_FIELD = "picture";
    public static final String DATE_FIELD = "createdAt";
    public static final String COMMENTS_FIELD = "comments";
    private ObjectId id;
    private String title;
    private String author;
    private String text;
    private String picture;
    private Date createdAt;
    private List<Comment> comments;


    private Vijest() {
    }

//    public Vijest(String id, String author, String text, GridFSFile picture, DateTimeAtCreation dateTimeAtCreation) {
//        this.id = ObjectId.
//        this.author = author;
//        this.text = text;
//        this.picture = picture;
//        this.dateTimeAtCreation = dateTimeAtCreation;
//    }

    public static Vijest toVijest(Document dbObject) throws ClassCastException {
        //todo zamotaj literale u konstante
        Vijest v = new Vijest();
        v.setId(new ObjectId(String.valueOf(dbObject.get(ID_FIELD))));
        v.setTitle((String) dbObject.get(TITLE_FIELD));
        v.setAuthor(String.valueOf(dbObject.get(AUTHOR_FIELD)));
        v.setText(String.valueOf(dbObject.get(TEXT_FIELD)));
        v.setPicture((String) dbObject.get(PICTURE_FIELD));
        v.setDateTimeAtCreation((Date) dbObject.get(DATE_FIELD));
        //noinspection unchecked
        List<Document> l = (List<Document>) dbObject.get(COMMENTS_FIELD);
        List<Comment> cl = new ArrayList<>(l.size());
        for (Document d : l) {
            cl.add(new Comment(d.getString("text"), d.getDate("date")));
        }
        v.setComments(cl);
        return v;
    }

    public DBObject toDBObject() {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append(AUTHOR_FIELD, text).append(TEXT_FIELD, text)
                .append(PICTURE_FIELD, picture).append(DATE_FIELD, createdAt)
                .append(COMMENTS_FIELD, comments);
        if (id != null)
            builder = builder.append(ID_FIELD, id);
        return builder.get();
    }

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDateTimeAtCreation() {
        return createdAt;
    }

    public void setDateTimeAtCreation(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
