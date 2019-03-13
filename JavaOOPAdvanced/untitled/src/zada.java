public class zada {
    public static void main(String[] args) {
        int sum = 0;
        int j = 0;

        for (int i = 1; i < 2020; i++) {
            sum += i;

            if (sum > 2018){
                j = i;
                break;
            }
        }

        System.out.println(j);
        System.out.println(sum);
    }
}
