package darian.saric.nmbp.dao;

import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.types.ObjectId;

import java.util.List;

public interface DAO {
    List<Vijest> getNewsPage(int pageNumber);

    void storeComment(ObjectId newsId, Comment c);
}
