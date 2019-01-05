package darian.saric.nmbp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static darian.saric.nmbp.model.Vijest.*;
import static darian.saric.nmbp.util.AppConstants.COLLECTION_NAME;

public class MongoDAO implements DAO {
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
                .forEach(document -> {
                    try {
                        news.add(Vijest.toVijest(document));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
//        news.forEach(n -> {
//            List<Comment> l = n.getComments();
//            List<Comment> newL = new ArrayList<>(l.size());
//
//        });
        return news;
    }


//    @Override
//    public Map<Vijest, List<Comment>> getNewsAndComments(List<Vijest> news) {
//        return null;
//    }

    @Override
    public void storeComment(ObjectId newsId, Comment c) {
        collection.updateOne(Filters.eq(ID_FIELD, newsId),
                Updates.push(COMMENTS_FIELD, c));

    }
}
