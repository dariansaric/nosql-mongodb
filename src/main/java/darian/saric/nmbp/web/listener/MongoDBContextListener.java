package darian.saric.nmbp.web.listener;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.UnknownHostException;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
        ((MongoClient) sce.getServletContext()
                .getAttribute("MONGO_CLIENT")).close();
        System.out.println("MongoClient closed successfully");
    }

    public void contextInitialized(ServletContextEvent sce) {
        //todo provjeriti radi li
        ServletContext ctx = sce.getServletContext();
        MongoCredential credential = MongoCredential.createCredential(
                ctx.getInitParameter("MONGODB_USERNAME"),
                ctx.getInitParameter("MONGODB_DATABASE"),
                ctx.getInitParameter("MONGODB_PASSWORD").toCharArray());
        MongoClient mongo = new MongoClient(
                ctx.getInitParameter("MONGODB_HOST"),
                Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));

        System.out.println("MongoClient initialized successfully");
        sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
    }

}
