package darian.saric.nmbp.web.listener;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Collections;

import static darian.saric.nmbp.util.AppConstants.*;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
        ((MongoClient) sce.getServletContext()
                .getAttribute(MONGO_CLIENT)).close();
        System.out.println("MongoClient closed successfully");
    }

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
//        CodecRegistry registry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
//                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//        MongoCredential credential = MongoCredential.createCredential(
//                ctx.getInitParameter(MONGO_USERNAME),
//                ctx.getInitParameter(MONGO_DATABASE),
//                ctx.getInitParameter(MONGO_PASSWORD).toCharArray());
        MongoClient mongo = (MongoClient) MongoClients.create(MongoClientSettings.builder()

                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(
                                new ServerAddress(ctx.getInitParameter(MONGO_HOST),
                                        Integer.parseInt(ctx.getInitParameter(MONGO_PORT))))))

                .credential(MongoCredential.createCredential(
                        ctx.getInitParameter(MONGO_USERNAME),
                        ctx.getInitParameter(MONGO_DATABASE),
                        ctx.getInitParameter(MONGO_PASSWORD).toCharArray()))

                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClient.getDefaultCodecRegistry(),
                        CodecRegistries.fromProviders(
                                PojoCodecProvider.builder()
                                        .automatic(true).build())))

                .build());

        System.out.println("MongoClient initialized successfully");
        sce.getServletContext().setAttribute(MONGO_CLIENT, mongo);
    }

}
