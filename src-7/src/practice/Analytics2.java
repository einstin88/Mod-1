package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analytics2 {
    static final String SPLIT_CSV = ",(?=(?:[^\"]\"[^\"]\")*[^\"]*$)";
    static final String RATING = "Rating";
    static final String CATEGORY = "Category";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        Integer indexGenre = 0;
        Integer indexRating = 0;
        Map<String, List<MyTuple>> extractedData;

        // Get the required column numbers
        String[] headers = br.readLine().split(SPLIT_CSV);
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(CATEGORY)) {
                indexGenre = i;
            }
            if (headers[i].equals(RATING)) {
                indexRating = i;
            }
        }

        // Read file to stream and extract required column data
        extractedData = br.lines()
                .map(line -> {
                    String[] lineData = line.split(SPLIT_CSV);
                    MyTuple tuple = new MyTuple(lineData[2].trim(), Float.parseFloat(lineData[1]));
                    return tuple;
                })
                .filter(tuple -> !tuple.rating.isNaN())
                .collect(Collectors.groupingBy(res -> res.genre));
        br.close();

        // Perform operation on the extracted data
        for (String genre : extractedData.keySet()) {
            Optional<Float> total;
            List<MyTuple> tuples = extractedData.get(genre);

            total = tuples.stream()
                    .flatMap(tuple -> Stream.of(tuple.rating))
                    .reduce((acc, rating) -> acc += rating);
        }
    }
}
