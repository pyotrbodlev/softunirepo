package problem_vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] carInfo = reader.readLine().split(" ");
        String[] truckInfo = reader.readLine().split(" ");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");

            switch (line[0]){
                case "Drive":
                    switch (line[1]){
                        case "Car":
                            System.out.println(car.drive(Double.parseDouble(line[2])));
                            break;
                        case "Truck":
                            System.out.println(truck.drive(Double.parseDouble(line[2])));
                            break;
                    }
                    break;
                case "Refuel":
                    switch (line[1]){
                        case "Car":
                            car.refuel(Double.parseDouble(line[2]));
                            break;
                        case "Truck":
                            truck.refuel(Double.parseDouble(line[2]));
                            break;
                    }
                    break;
            }
        }

        System.out.println(car.getResult());
        System.out.println(truck.getResult());

    }
}
