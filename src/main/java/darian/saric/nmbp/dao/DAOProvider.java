package darian.saric.nmbp.dao;




public class DAOProvider {

    private static DAO dao = new MongoDAO();

    public static DAO getDAO() {
        return dao;
    }

}