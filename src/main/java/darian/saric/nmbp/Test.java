package darian.saric.nmbp;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        MongoCredential c = MongoCredential.createCredential(
                "web",
                "nmbp",
                "webpass123".toCharArray()
        );

        MongoClient m = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress("localhost", 27017))))
                .credential(c)
                .build());

        System.out.println(m.getDatabase("nmbp").listCollectionNames().first());

        m.close();


    }
}
