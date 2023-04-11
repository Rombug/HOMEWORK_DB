package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;
public class QuestionAplication {

    public void question () {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("exam_db");
        MongoCollection<Document> collection = database.getCollection("questions");
        MongoCollection<Document> users = database.getCollection("users");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Paspauskite Enter, kad pradėtumėte testą ");
        String name = scanner.nextLine();

        int score = 0;
        int totalQuestions = (int) collection.count();

        for (Document question : collection.find()) {

            System.out.println(question.get("question"));
            System.out.println("a) " + question.get("option_a"));
            System.out.println("b) " + question.get("option_b"));
            System.out.println("c) " + question.get("option_c"));

            System.out.print("Jūsų atsakymas yra: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase((String) question.get("answer"))) {
                System.out.println("Jūs atsakėte teisingai!");
                score++;
            } else {
                System.out.println("Deje, bet Jūsų atsakymas yra neteisingas :(");
            }
            System.out.println();
        }

        double mark = ((double) score / totalQuestions * 10);
        int roundedMark = (int) Math.ceil(mark - 0.5);

        System.out.println("Jūs atsakėte teisingai į " + score + " iš " + collection.count() + ".");
        System.out.println("Jūsų galutinis vertinimas balais: " + roundedMark);
        System.out.println("Jūs surinkote: " + mark);

        scanner.close();
        mongoClient.close();
    }
}
