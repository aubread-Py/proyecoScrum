package record;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import card.Card;

public class Record {

    private static int id;
    BufferedWriter writer;

    public Record() {

        try {
            id = getRecordCount() + 1; // avoid 0
            Path path = Path.of("proyectoScrum", "res", "record", id + "-record");
            writer = new BufferedWriter(new FileWriter(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(String action, String playerName, Card card, int turnNumber) throws IOException {

        writer.write("turn " + turnNumber);
        writer.newLine();

        switch (action) {

            case "PLAYS":
                writer.write(playerName + " plays " + card);
                break;

            case "DRAWS":
                writer.write(playerName + " draws " + card);

            case "WINS":
                writer.write(playerName + " wins!");
                break;

            default:
                break;
        }

        writer.newLine();

    }

    public void close() throws IOException {
        writer.close();
    }

    private static Stream<Path> getRecordsFiles() throws IOException {
        return Files.list(Path.of("proyectoScrum", "res", "record"));
    }

    public static List<Path> getRecords() throws IOException {
        try (var files = getRecordsFiles()) {
            return files.toList();
        }
    }

    public static int getRecordCount() throws IOException {
        try (var files = getRecordsFiles()) {
            return (int) files.count();
        }
    }

}
