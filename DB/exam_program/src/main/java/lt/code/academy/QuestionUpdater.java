package lt.code.academy;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Scanner;

public class QuestionUpdater {
    public void updater (){

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("exam_db");

        MongoCollection<Document> questions = database.getCollection("questions");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Pasirinkite veiksmą iš MENIU:");
            System.out.println("1. Pridėti klausimą.");
            System.out.println("2. Atnaujinti klausimą.");
            System.out.println("3. Ištrinti klausimą.");
            System.out.println("4. Išeiti iš programos.");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    addQuestion(questions, scanner);
                    break;
                case 2:
                    updateQuestion(questions, scanner);
                    break;
                case 3:
                    deleteQuestion(questions, scanner);
                    break;
                case 4:
                    mongoClient.close();
                    return;
                default:
                    System.out.println("Tokio pasirinkimo nėra.");
                    break;
            }
        }
    }

    private static void addQuestion(MongoCollection<Document> questions, Scanner scanner) {
        System.out.println("Įveskite klausimą: ");
        String question = scanner.nextLine();
        System.out.println("Įveskite atsakymo variantą A:");
        String optionA = scanner.nextLine();
        System.out.println("Įveskite atsakymo variantą B:");
        String optionB = scanner.nextLine();
        System.out.println("Įveskite atsakymo variantą C:");
        String optionC = scanner.nextLine();
        System.out.println("Įveskite teisingo atsakymo variantą (A, B, C):");
        String answer = scanner.nextLine();

        Document document = new Document()
                .append("question", question)
                .append("option_a", optionA)
                .append("option_b", optionB)
                .append("option_c", optionC)
                .append("answer", answer);

        questions.insertOne(document);

        System.out.println("Klausimas pridėtas sėkmingai.");
    }


    private static void updateQuestion(MongoCollection<Document> questions, Scanner scanner) {

        System.out.print("Įveskite klausimo ID kurį norite atnaujinti (pataisyti): ");
        String idString = scanner.nextLine();
        ObjectId id = new ObjectId(idString);

        Document query = new Document("_id", id);
        Document question = questions.find(query).first();

        if (question == null) {
            System.out.println("Deja, bet klausimas pagal įvestą ID nerastas.");
        } else {
            System.out.println("Šiuo metu DB esantis klausimas: " + question.getString("question"));
            System.out.println("Atsakymo variantas A: " + question.getString("option_a"));
            System.out.println("Atsakymo variantas B: " + question.getString("option_b"));
            System.out.println("Atsakymo variantas C: " + question.getString("option_c"));
            System.out.println("Teisingas atsakymas: " + question.getString("answer"));

            System.out.print("Įveskite klausimą: ");
            String newQuestion = scanner.nextLine();
            System.out.print("Įveskite atsakymo variantą A: ");
            String newOptionA = scanner.nextLine();
            System.out.print("Įveskite atsakymo variantą B: ");
            String newOptionB = scanner.nextLine();
            System.out.print("Įveskite atsakymo variantą C: ");
            String newOptionC = scanner.nextLine();
            System.out.print("Įveskite teisingo atsakymo variantą (A, B, C): ");
            String newAnswer = scanner.nextLine();

            questions.updateOne(query, Updates.combine(
                    Updates.set("question", newQuestion),
                    Updates.set("option_a", newOptionA),
                    Updates.set("option_b", newOptionB),
                    Updates.set("option_c", newOptionC),
                    Updates.set("answer", newAnswer)));

            System.out.println("Klausimas sėkmingai atnaujintas.");
        }
    }

    private static void deleteQuestion(MongoCollection<Document> questions, Scanner scanner){

        System.out.print("Įveskite klausimo ID kurį norite ištrinti: ");
        String idString = scanner.next();
        ObjectId questionToDelete = new ObjectId(idString);

        Document query = new Document("_id", questionToDelete);
        Document question = questions.find(query).first();

        if (question == null) {
            System.out.println("Deja, bet klausimas pagal įvestą ID nerastas.");
        } else {

            System.out.println("Šiuo metu DB esantis klausimas: " + question.getString("question"));
            System.out.println("Atsakymo variantas A: " + question.getString("option_a"));
            System.out.println("Atsakymo variantas B: " + question.getString("option_b"));
            System.out.println("Atsakymo variantas C: " + question.getString("option_c"));
            System.out.println("Teisingas atsakymas: " + question.getString("answer"));

            System.out.print("Ar Jūs tikrai norite ištrinti šį klausimą? (y/n) ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("y")) {

                questions.deleteOne(query);
                System.out.println("Klausimas ištrintas sėkmingai.");
            } else {
                System.out.println("Klausimo trinimas atšauktas.");
            }
        }
    }
}
