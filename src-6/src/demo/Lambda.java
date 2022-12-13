package demo;

import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        // hi is a variable with 1 assigned function

        Greetings hi = (name) -> {
            System.out.println("Hello " + name);
        };
        hi.Hello("Pelie");

        List<String> names = List.of("asdbgf", "asdlkj", "oeupwoigi");
        for (String name : names) {
            Apply(hi, name);
        }
    }
 
    public static void Apply(Greetings greetings, String name) {
        greetings.Hello(name);
    }

}
