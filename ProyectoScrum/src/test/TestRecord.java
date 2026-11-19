package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import card.Card;
import record.Record;

public class TestRecord {
    public static void main(String[] args) throws IOException {

        Path path = Path.of("proyectoScrum", "res", "record");

        System.out.println(path.toAbsolutePath());
        System.out.println(Files.exists(path)); // true
        System.out.println(Files.isDirectory(path)); // true

        System.out.println(System.getProperty("user.dir"));

        try (var files = Files.list(Path.of("."))) {
            files.forEach(System.out::println);
        }

        Record record = new Record();

        record.write("PLAYS", "TestPlayer", new Card("RED", "ONE"), 0);
        record.write("DRAWS", "TestPlayer", new Card("RED", "ONE"), 1);
        record.write("WINS", "TestPlayer", null, 2);
        record.close();
    }
}
