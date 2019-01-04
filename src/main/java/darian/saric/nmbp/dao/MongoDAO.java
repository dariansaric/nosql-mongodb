package darian.saric.nmbp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static darian.saric.nmbp.model.Vijest.*;

public class MongoDAO implements DAO {
    private static final String COLLECTION_NAME = "news";
    private MongoCollection<Document> collection;

    public MongoDAO(MongoClient client, String databaseName) {
        collection = client.getDatabase(databaseName).getCollection(COLLECTION_NAME);
    }

//    private List<Vijest> getTop10News() {
//        return getNewsPage(1);
//    }

    @Override
    public List<Vijest> getNewsPage(int pageNumber) {
        List<Vijest> news = new ArrayList<>(10);

        collection.find()
                .sort(Sorts.descending(DATE_FIELD))
                .skip(10 * (pageNumber - 1))
                .limit(10)
                .into(new ArrayList<>())
                .forEach(document -> news.add(Vijest.toVijest(document)));
        return news;
    }

    @Override
    public List<Comment> getCommentsForNews(ObjectId newsId) {
        List<Comment> comments = new LinkedList<>();
        collection.find(Filters.eq(ID_FIELD, newsId))
                .projection(Projections.fields(Projections.include(COMMENTS_FIELD)))
                .into(new ArrayList<>()).forEach(d -> comments.add(Comment.toComment(d)));

        return comments;
    }

//    @Override
//    public Map<Vijest, List<Comment>> getNewsAndComments(List<Vijest> news) {
//        return null;
//    }

    @Override
    public void storeComment(ObjectId newsId, Comment c) {
        collection.findOneAndUpdate(Filters.eq(ID_FIELD, newsId),
                Updates.addToSet(COMMENTS_FIELD, c));

    }
}
