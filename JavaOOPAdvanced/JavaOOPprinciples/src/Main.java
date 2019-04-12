public class Main {
    public static void main(String[] args) {
        Superhero batman = new Superhero("Bruce Wayne");
        batman.addPower("SUPERPOWER");
        batman.getPowers().remove(0);

        batman.getPowers().forEach(p -> System.out.println(p));
    }
}
