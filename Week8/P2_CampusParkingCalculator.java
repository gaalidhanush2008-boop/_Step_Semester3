import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 8 - Problem 2: The Campus Parking Charge Calculator
 *
 * Input:  N, then N lines of "BIKE|CAR|TRUCK hours"
 */
public class P2_CampusParkingCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            vehicles.add(createVehicle(type, hours));
        }

        double total = 0;
        for (Vehicle v : vehicles) {
            double charge = v.charge();
            total += charge;
            System.out.println(v.label() + ": " + String.format(Locale.US, "%.2f", charge));
        }
        System.out.println("Total: " + String.format(Locale.US, "%.2f", total));
    }

    static Vehicle createVehicle(String type, int hours) {
        return switch (type) {
            case "BIKE" -> new Bike(hours);
            case "CAR" -> new Car(hours);
            case "TRUCK" -> new Truck(hours);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}

abstract class Vehicle {
    protected final int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract String label();
    abstract double charge();
}

class Bike extends Vehicle {
    Bike(int hours) { super(hours); }
    String label() { return "BIKE"; }
    double charge() { return 10.0 * hours; }                          // Rs 10 per hour
}

class Car extends Vehicle {
    Car(int hours) { super(hours); }
    String label() { return "CAR"; }
    double charge() { return 30.0 + 20.0 * (hours - 1); }             // Rs 30 first hour + Rs 20 each extra
}

class Truck extends Vehicle {
    Truck(int hours) { super(hours); }
    String label() { return "TRUCK"; }
    double charge() { return Math.max(50.0 * hours, 100.0); }         // Rs 50/hour, minimum Rs 100
}
