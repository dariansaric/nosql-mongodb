package darian.saric.nmbp.dao;

import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public class MongoDAO implements DAO {
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
}
