package practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Analytics {
    static final String COL_1 = "Rating";
    static final String COL_2 = "Category";

    public static void main(String[] args) {
        Integer indexRating = 0;
        Integer indexGenre = 0;
        Map<String, List<Float>> extractedData = new HashMap<>();

        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);

            String[] headers = br.readLine().split(",");

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(COL_1)) {
                    indexRating = i;
                } else if (headers[i].equals(COL_2)) {
                    indexGenre = i;
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                Float rating;
                String genre;

                String[] lineData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                genre = lineData[indexGenre].trim();
                rating = Float.parseFloat(lineData[indexRating]);
                if (rating.isNaN()) {
                    continue;
                }

                List<Float> temp = extractedData.getOrDefault(genre, new LinkedList<>());
                temp.add(rating);
                extractedData.put(genre, temp);
            }
            br.close();

            for (String genre : extractedData.keySet().stream().sorted().toList()) {
                List<Float> genreRatings = extractedData.get(genre);
                Float genreSum = genreRatings.stream()
                        .reduce(0f, (acc, n) -> {
                            return acc += n;
                        });

                System.out.printf(">> Genre: %s -> Avg Rating: %.4f%n",
                        genre, genreSum / genreRatings.size());
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
