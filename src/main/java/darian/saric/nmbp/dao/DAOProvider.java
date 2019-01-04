package darian.saric.nmbp.dao;


import com.mongodb.client.MongoClient;

public class DAOProvider {

    private static DAO dao;

    public static DAO getDAO() {
        return dao;
    }

    public static void initDAO(MongoClient client, String databaseName) {
        dao = new MongoDAO(client, databaseName);
    }
}