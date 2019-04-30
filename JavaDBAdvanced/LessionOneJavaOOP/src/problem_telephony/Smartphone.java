package problem_telephony;

public class Smartphone implements Callable, Browsable {

    @Override
    public String browse(String website) {
        if (website.chars().anyMatch(Character::isDigit)){
            throw new IllegalArgumentException("Invalid URL!");
        }

        return "Browsing: " + website + "!";
    }

    @Override
    public String call(String number) {
        return "Calling... " + number;
    }
}
