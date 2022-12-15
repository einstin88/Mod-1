package practice;

import java.util.HashMap;
import java.util.Map;

public class Position {
    String id;
    String name;
    String description;
    Map<String, String> options;

    
    public Position(String id) {
        this(id, "", "", new HashMap<>());
    }

    public Position(String id, String name, String description, Map<String, String> options) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.options = options;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = (Position) obj;
        if (id.equals(pos.getId())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "%s%n==========%n%s%n----------".formatted(name, description);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getOptions() {
        return options;
    }

}
