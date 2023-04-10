package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class DataBaseCreator {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("exam_db");
        MongoCollection<Document> collection = database.getCollection("questions");


        Document question1 = new Document("question", "Koks yra MongoDB skirtumas nuo tradicinių reliacinių duomenų bazių?")
                .append("option_a", "MongoDB yra į dokumentus orientuota duomenų bazė, o reliacinės duomenų bazės yra orientuotos į lenteles.")
                .append("option_b", "MongoDB yra duomenų bazių tipo vardu, o reliacinės duomenų bazės yra SQL duomenų bazių tipo.")
                .append("option_c", "MongoDB yra objektinės programavimo kalbos, o reliacinės duomenų bazės yra procedūrinės programavimo kalbos.")
                .append("answer", "a");

        Document question2 = new Document("question", "Kokie yra MongoDB privalumai?")
                .append("option_a", "Lengva naudoti, greita, lanksti ir gali apdoroti didelius kiekius duomenų.")
                .append("option_b", "Sudėtinga naudoti, lėta, ribota ir negali apdoroti didelių kiekių duomenų.")
                .append("option_c", "Prieinama tik profesionalams, brangi ir reikalauja daug laiko diegimui ir konfigūravimui.")
                .append("answer", "a");

        Document question3 = new Document("question", "Kaip MongoDB saugo duomenis?")
                .append("option_a", "MongoDB saugo duomenis lentelėse.")
                .append("option_b", "MongoDB saugo duomenis dokumentuose.")
                .append("option_c", "MongoDB saugo duomenis failuose.")
                .append("answer", "b");

        Document question4 = new Document("question", "Kokią kalbą MongoDB naudoja užklausų rašymui ir skaitymui?")
                .append("option_a", "SQL")
                .append("option_b", "C++")
                .append("option_c", "JavaScript")
                .append("answer", "c");

        Document question5 = new Document("question", "Kaip MongoDB saugo duomenų santykius?")
                .append("option_a", "Naudojant join operacijas.")
                .append("option_b", "Naudojant integruotas papildomų duomenų rinkinius.")
                .append("option_c", "Naudojant denormalizaciją.")
                .append("answer", "c");

        Document question6 = new Document("question", "Kaip galima pridėti naują dokumentą į MongoDB duomenų bazę?")
                .append("option_a", "Naudoti \"INSERT\" užklausą.")
                .append("option_b", "Naudoti \"ADD\" užklausą.")
                .append("option_c", "Naudoti \"CREATE\" užklausą.")
                .append("answer", "a");

        Document question7 = new Document("question", "Kokia yra PostgreSQL paskirtis?")
                .append("option_a", "Duomenų bazės valdymas.")
                .append("option_b", "Dokumentų kūrimas.")
                .append("option_c", "Interneto naršyklės kūrimas.")
                .append("answer", "a");

        Document question8 = new Document("question", "Kokios yra PostgreSQL savybės?")
                .append("option_a", "Open-source, reliacinė, transakcinė")
                .append("option_b", "Uždaro kodo, reliacinė, transakcinė")
                .append("option_c", "Open-source, ne reliacinė, nesusijusi su transakcijomis")
                .append("answer", "a");

        Document question9 = new Document("question", "Koks yra PostgreSQL standartinis TCP/IP portas?")
                .append("option_a", "3306")
                .append("option_b", "5432")
                .append("option_c", "8080")
                .append("answer", "b");

        Document question10 = new Document("question", "Kokią SQL kalbos versiją palaiko PostgreSQL?")
                .append("option_a", "SQL-86")
                .append("option_b", "SQL-92")
                .append("option_c", "SQL-2011")
                .append("answer", "c");

        collection.insertOne(question1);
        collection.insertOne(question2);
        collection.insertOne(question3);
        collection.insertOne(question4);
        collection.insertOne(question5);
        collection.insertOne(question6);
        collection.insertOne(question7);
        collection.insertOne(question8);
        collection.insertOne(question9);
        collection.insertOne(question10);

        System.out.println("Database: " + database.getName() + " susikūrė sėkmingai");
        System.out.println("Collection: " + collection.getNamespace().getCollectionName() + " susikūrė sėkmingai");

        mongoClient.close();
    }
}
