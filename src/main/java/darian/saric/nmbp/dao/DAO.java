package darian.saric.nmbp.dao;

import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface DAO {
    List<Vijest> getTop10News();

    List<Comment> getCommentsForNews(ObjectId newsId);

    Map<Vijest, List<Comment>> getNewsAndComments(List<Vijest> news);

    void storeComment(ObjectId newsId, Comment c);
    //todo map-reduce upiti
}