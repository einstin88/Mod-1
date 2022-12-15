package demo;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
// import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class IntList {
    public static void main(String[] args) {
        // Randomly generate a list
        Integer max = 300;
        Integer range = 100;
        Random rand = new SecureRandom();

        List<Integer> numList = new LinkedList<>();
        for (int i = 0; i < max; i++) {
            numList.add(rand.nextInt(range));
        }

        numList = numList.stream()
                .sorted().distinct()
                .toList();

        // FilterList(numList);
        // MapList(numList);
        // JoinList(numList);
        ReduceList(numList);
    }

    public static void FilterList(List<Integer> numList) {

        List<Integer> filteredList = new LinkedList<>();

        filteredList = numList.stream()
                // Predicate: boolean test(Integer i)
                // https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/util/function/Predicate.html#test(T)
                .filter(i -> (i % 3 == 0))
                .distinct()
                .sorted()
                .toList();

        System.out.println(filteredList);

        System.out.println(filteredList.stream()
                .limit(5)
                .toList());

        // Equivalent operation
        // for (int n : numList) {
        // if ((n % 3) != 0) {
        // continue;
        // }
        // filteredList.add(n);
        // }
    }

    public static void MapList(List<Integer> numList) {
        List<Integer> mappedList = new LinkedList<>();

        mappedList = numList.stream()
                .sorted().distinct()
                // map : String apply (Integer i)
                .map(n -> "%d%d".formatted(n, n))
                // map : Integer apply (String s)
                .map(Integer::parseInt)
                // .map(i -> Integer.parseInt("%d%d".formatted(i, i)))
                .toList();

        System.out.println(mappedList);
    }

    public static void JoinList(List<Integer> numList) {
        String joinedList;

        joinedList = numList.stream()
                .map(n -> "%d%d".formatted(n, n))
                .collect(Collectors.joining(","));

        System.out.println(joinedList);
    }

    public static void ReduceList(List<Integer> numList) {
        Integer sum;

        sum = numList.stream()
                .map(n -> "%d%d".formatted(n, n))
                .map(Integer::parseInt)
                .collect(
                Collectors.reducing(0, (acc, n) -> {
                return acc += n;
                }));

                System.out.println(sum);

        // https://docs.oracle.com/en/java/javase/19/docs/api/java.base/java/util/stream/Collectors.html#reducing(java.util.function.BinaryOperator)
        // returns -> Optional<Integer>
        // .collect(
        // Collectors.reducing((acc, n) -> {
        // return acc += n;
        // }));

        // System.out.println(sum.isPresent() ? sum.get() : "null");

        // Equivalent operation
        // System.out.println(numList.stream()
        // .map(n -> "%d%d".formatted(n, n))
        // .map(Integer::parseInt)
        // .reduce((acc, n) -> {
        // return acc += n;
        // }));
    }
}
