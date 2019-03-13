package cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        System.out.println(line + ":");
        switch (line){
            case "Card Suits":
                for (CardSuits value : CardSuits.values()) {
                    System.out.println(value.toString());
                }
                break;
            case "Card Ranks":
                for (CardRank value : CardRank.values()) {
                    System.out.println(value.toString());
                }
                break;
        }
    }

}
