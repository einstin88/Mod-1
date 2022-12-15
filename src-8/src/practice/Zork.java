package practice;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Zork {
    static final String FILE_PATH = "zork.txt";

    static Position currentPos;
    static List<Position> rooms = new LinkedList<>();

    public static void main(String[] args) {
        try {
            LoadData(FILE_PATH);
            System.out.printf("Loaded %s data to Zork!%n", FILE_PATH);
        } catch (IOException e) {
        }

        Console cons = System.console();
        String input;
        Boolean validInput = true;
        while (true) {
            if (validInput) {
                input = cons.readLine("==========%nYou are at:%n%s%n>> ".formatted(currentPos));
                input = input.toLowerCase().trim();
            } else {
                input = cons.readLine(">> ");
                input = input.toLowerCase().trim();
            }

            if (currentPos.getOptions().containsKey(input)) {
                currentPos = UpdatePosition(currentPos.getOptions().get(input));
                validInput = true;
            } else if (input.equals("exit")) {
                System.out.println("Goodbye!\n");
                break;
            } else if (input.equals("look")) {
                validInput = true;
                continue;
            } else {
                System.out.println(input + " is not a valid option\n");
                validInput = false;
            }
        }

    }

    static void LoadData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        Boolean newRoom = false;
        String id = "", name = "", description = "";
        Map<String, String> directions = new HashMap<>();

        while ((line = br.readLine()) != null) {
            String[] lineData = line.split(": ");

            switch (lineData[0].trim()) {
                case "room" -> {
                    id = lineData[1].trim();
                    newRoom = true;
                }
                case "name" -> {
                    name = lineData[1].trim();
                }
                case "description" -> {
                    description = lineData[1].trim().replaceAll("<break>", "\n");
                }
                case "direction" -> {
                    String[] commands = lineData[1].split(" ");
                    directions.put(commands[0].trim(), commands[1].trim());
                }
                case "start" -> {
                    currentPos = UpdatePosition(lineData[1]);
                }
                default -> {
                    if (newRoom) {
                        rooms.add(new Position(id, name, description, directions));
                    }
                    directions = new HashMap<>();
                    newRoom = false;
                }
            }
        }
        br.close();
    }

    static Position UpdatePosition(String id) {
        return rooms.get(
                rooms.indexOf(
                        new Position(id)));
    }
}
