import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 9 - Problem 4: City Cab Fare Meter
 * The minimum-fare rule is shared (written once in abstract class Cab).
 * Night service is an ability only some cabs have, so it is an interface (NightService).
 * A night trip in a cab that is not a NightService is rejected.
 *
 * Input:  N, then N lines of "MINI|SEDAN|SUV km DAY|NIGHT"
 */
public class P4_CityCabFareMeter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double total = 0;
        for (int i = 0; i < n; i++) {
            String cabType = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();

            Cab cab = createCab(cabType);
            double fare = cab.fare(km);

            if (time.equals("NIGHT")) {
                if (cab instanceof NightService) {
                    fare = ((NightService) cab).applyNightSurcharge(fare);
                } else {
                    System.out.println(cab.name() + ": night service not available");
                    continue;
                }
            }
            total += fare;
            System.out.println(cab.name() + ": " + String.format(Locale.US, "%.2f", fare));
        }
        System.out.println("Total: " + String.format(Locale.US, "%.2f", total));
    }

    static Cab createCab(String type) {
        return switch (type) {
            case "MINI" -> new MiniCab();
            case "SEDAN" -> new SedanCab();
            case "SUV" -> new SuvCab();
            default -> throw new IllegalArgumentException("Unknown cab type: " + type);
        };
    }
}

interface NightService {
    double NIGHT_SURCHARGE = 0.20;     // 20% extra

    default double applyNightSurcharge(double fare) {
        return fare * (1 + NIGHT_SURCHARGE);
    }
}

abstract class Cab {
    private static final double MINIMUM_FARE = 100;     // one rule for every cab

    abstract String name();
    abstract double ratePerKm();

    final double fare(double km) {
        return Math.max(km * ratePerKm(), MINIMUM_FARE);
    }
}

class MiniCab extends Cab {
    String name() { return "MINI"; }
    double ratePerKm() { return 10; }
}

class SedanCab extends Cab implements NightService {
    String name() { return "SEDAN"; }
    double ratePerKm() { return 14; }
}

class SuvCab extends Cab implements NightService {
    String name() { return "SUV"; }
    double ratePerKm() { return 18; }
}
