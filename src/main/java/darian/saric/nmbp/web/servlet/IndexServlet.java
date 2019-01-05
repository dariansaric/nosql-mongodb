package darian.saric.nmbp.web.servlet;

import darian.saric.nmbp.dao.DAO;
import darian.saric.nmbp.model.Comment;
import darian.saric.nmbp.model.Vijest;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static darian.saric.nmbp.dao.DAOProvider.getDAO;
import static darian.saric.nmbp.util.AppConstants.PAGES_PATH;

@WebServlet(urlPatterns = {"/", "/home"}, name = "index")
public class IndexServlet extends HttpServlet {
    //todo pospremiti slike u zasebnu kolekciju i jednim servletom ih nasumično dohvaćati
    private static final String NEWS_ID_PARAMETER = "id";
    private static final String PAGE_PARAMETER = "page";
    private static final String NEWS_ATTRIBUTE = "news";
    private static final String COMMENT_ATTRIBUTE = "comment";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        try {
            if (req.getParameter(PAGE_PARAMETER) != null) {
                page = Integer.parseInt(req.getParameter(PAGE_PARAMETER));
                page = page < 1 ? 1 : page;
            }
        } catch (NumberFormatException ignored) {
        }

        generateResponse(page, getDAO(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(PAGE_PARAMETER) == null || req.getParameter(NEWS_ID_PARAMETER) == null ||
                req.getParameter(COMMENT_ATTRIBUTE) == null) {
            req.getRequestDispatcher(PAGES_PATH + "index.jsp").forward(req, resp);
            return;
        }
        int page;
        try {
            page = Integer.parseInt(req.getParameter(PAGE_PARAMETER));
            page = page < 1 ? 1 : page;
        } catch (NumberFormatException e) {
            resp.sendError(400);
            return;
        }

        ObjectId id = new ObjectId(req.getParameter(NEWS_ID_PARAMETER));
        DAO dao = getDAO();
        dao.storeComment(id, new Comment(req.getParameter(COMMENT_ATTRIBUTE)));

        generateResponse(page, dao, req, resp);
    }

    private void generateResponse(int page, DAO dao, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vijest> vijesti = dao.getNewsPage(page);

        req.setAttribute(PAGE_PARAMETER, page);
        req.setAttribute(NEWS_ATTRIBUTE, vijesti);
        req.getRequestDispatcher(PAGES_PATH + "index.jsp").forward(req, resp);
    }
}
