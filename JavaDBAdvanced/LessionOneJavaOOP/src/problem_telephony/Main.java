package problem_telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] numbers = reader.readLine().split(" ");
        String[] websites = reader.readLine().split(" ");

        Smartphone smartphone = new Smartphone();

        for (String number : numbers) {
            System.out.println(smartphone.call(number));
        }

        for (String website : websites) {
            try {
                System.out.println(smartphone.browse(website));
            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
            }
        }
    }
}
