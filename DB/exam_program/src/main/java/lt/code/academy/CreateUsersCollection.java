package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CreateUsersCollection {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("exam_db");
        MongoCollection<Document> collection = database.getCollection("users");

        Document student1 = new Document("username", "Romanas")
                .append("role", "student");
        collection.insertOne(student1);

        Document student2 = new Document("username", "Saule")
                .append("role", "student");
        collection.insertOne(student2);

        Document lecturer1 = new Document("username", "Alfas")
                .append("role", "lecturer");
        collection.insertOne(lecturer1);

        Document lecturer2 = new Document("username", "Vidmantas")
                .append("role", "lecturer");
        collection.insertOne(lecturer2);

        System.out.println("Collection: " + collection.getNamespace().getCollectionName() + " susikūrė sėkmingai");

        mongoClient.close();
    }
}
