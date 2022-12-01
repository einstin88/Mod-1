package Work_1;

public class ToDo {
    public static void main(String[] args) {
        String[] tasks = new String[3];

        tasks[0] = "running";
        tasks[1] = "eating";
        tasks[2] = "sleeping";

        Integer i = 0;

        while (i < tasks.length) {
            System.out.println((i+1) + ". " + tasks[i]);
            i++;
        }
    }
}
