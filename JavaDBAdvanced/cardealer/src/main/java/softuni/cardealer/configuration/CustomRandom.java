package softuni.cardealer.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomRandom {
    private static List<Integer> numbers = new ArrayList<>();

    public static int nextInt(int minBound, int maxBound){
        while (true){
            ThreadLocalRandom random = ThreadLocalRandom.current();

            int i = random.nextInt(minBound, maxBound);

            if (!numbers.contains(i)){
                numbers.add(i);
                return i;
            }
        }
    }

    public static void clearCache(){
        numbers.clear();
    }
}
