package darian.saric.nmbp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

import static darian.saric.nmbp.util.AppConstants.MONGO_DATABASE;

public class MongoDAO implements DAO {
    private MongoDatabase database;

    public MongoDAO(MongoClient client) {
        database = client.getDatabase(MONGO_DATABASE);
    }

    @Override
    public List<Vijest> getTop10News() {
        return null;
    }

    @Override
    public List<Comment> getCommentsForNews(ObjectId newsId) {
        return null;
    }

    @Override
    public Map<Vijest, List<Comment>> getNewsAndComments(List<Vijest> news) {
        return null;
    }

    @Override
    public void storeComment(ObjectId newsId, Comment c) {
        MongoCollection<Document> collection = database.getCollection("news");
        collection.findOneAndUpdate(Filters.eq("_id", newsId),
                Updates.addToSet("comments", c));

    }
}
