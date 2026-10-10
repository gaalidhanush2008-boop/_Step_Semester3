import java.util.Locale;
import java.util.Scanner;

/**
 * Week 9 - Problem 5: Home Appliance Energy Report
 * Every appliance has a power rating and computes units the same way (abstract class).
 * Only some appliances have a saver mode, so that is an interface (HasSaverMode).
 *
 * Input:  N, then N lines of "FRIDGE|AC|TV|WASHER hours" or "... hours SAVER"
 */
public class P5_HomeApplianceEnergyReport {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();                         // move to the start of the first appliance line

        double totalCost = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            Appliance appliance = createAppliance(parts[0]);
            double hours = Double.parseDouble(parts[1]);
            boolean saverRequested = parts.length > 2 && parts[2].equals("SAVER");

            double units = appliance.units(hours);
            if (saverRequested) {
                if (appliance instanceof HasSaverMode) {
                    units = ((HasSaverMode) appliance).applySaver(units);
                } else {
                    System.out.println(appliance.name() + ": saver mode not supported");
                    continue;
                }
            }
            double cost = units * Appliance.RATE_PER_UNIT;
            totalCost += cost;
            System.out.println(appliance.name() + ": Units=" + fmt(units) + " Cost=" + fmt(cost));
        }
        System.out.println("Total Cost: " + fmt(totalCost));
    }

    static Appliance createAppliance(String type) {
        return switch (type) {
            case "FRIDGE" -> new Fridge();
            case "AC" -> new AirConditioner();
            case "TV" -> new Television();
            case "WASHER" -> new WashingMachine();
            default -> throw new IllegalArgumentException("Unknown appliance: " + type);
        };
    }

    static String fmt(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}

interface HasSaverMode {
    double SAVER_FACTOR = 0.75;        // saver mode cuts energy use by 25%

    default double applySaver(double units) {
        return units * SAVER_FACTOR;
    }
}

abstract class Appliance {
    static final double RATE_PER_UNIT = 8;

    abstract String name();
    abstract double powerWatts();

    double units(double hours) {
        return powerWatts() * hours / 1000;   // kWh
    }
}

class Fridge extends Appliance {
    String name() { return "FRIDGE"; }
    double powerWatts() { return 150; }
}

class AirConditioner extends Appliance implements HasSaverMode {
    String name() { return "AC"; }
    double powerWatts() { return 1500; }
}

class Television extends Appliance {
    String name() { return "TV"; }
    double powerWatts() { return 100; }
}

class WashingMachine extends Appliance implements HasSaverMode {
    String name() { return "WASHER"; }
    double powerWatts() { return 500; }
}
