package lt.code.academy;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {

        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);

        MongoDatabase database = MongoClients.create().getDatabase("exam_db");
        MongoCollection<Document> users = database.getCollection("users");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Įveskite savo vartotojo vardą: ");
        String username = scanner.nextLine();

        Document userDoc = users.find(new Document("username", username)).first();

        if (userDoc == null) {
            System.out.println("User not found.");
            return;
        }

        String role = userDoc.getString("role");
        if (role.equals("student")) {
            takeExam(username);
        } else if (role.equals("lecturer")) {
            correctQuestions(username);
        } else {
            System.out.println("Invalid role.");
        }
    }

    private static void takeExam(String username) {
        System.out.println("Sveiki studente " + username + "! Jūs galite laikyti egzaminą.");
        QuestionAplication questionAplication = new QuestionAplication();
        questionAplication.question();
    }

    private static void correctQuestions(String username) {
        System.out.println("Sveiki lektoriau " + username + "!");
        QuestionUpdater myUpdater = new QuestionUpdater();
        myUpdater.updater();
    }
}
