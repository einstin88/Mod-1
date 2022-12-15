package practice;

public class TestRegex {
    public static void main(String[] args) {
        String s = "\"Canva: Poster, banner, card maker & graphic design\",ART_AND_DESIGN,4.7,174531,24M,\"10,000,000+\",Free,0,Everyone,Art & Design,\"July 31, 2018\",1.6.1,4.1 and up";

        String[] res = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (String x : res) {
            System.out.println(x);
        }
    }
}
