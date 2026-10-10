import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 8 - Problem 3: The Hostel Electricity Bill
 * The extra "occupants" value lives inside SharedRoom, so the main flow treats every room alike.
 *
 * Input:  N, then N lines of "SINGLE units" | "SHARED units occupants" | "AC units"
 */
public class P3_HostelElectricityBill {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double units = sc.nextDouble();
            switch (type) {
                case "SINGLE" -> rooms.add(new SingleRoom(units));
                case "SHARED" -> rooms.add(new SharedRoom(units, sc.nextInt()));
                case "AC" -> rooms.add(new AcRoom(units));
                default -> throw new IllegalArgumentException("Unknown room type: " + type);
            }
        }

        double total = 0;
        for (Room r : rooms) {
            double bill = r.bill();
            total += bill;
            System.out.println(r.label() + ": " + String.format(Locale.US, "%.2f", bill));
        }
        System.out.println("Total: " + String.format(Locale.US, "%.2f", total));
    }
}

abstract class Room {
    protected final double units;

    Room(double units) {
        this.units = units;
    }

    abstract String label();
    abstract double bill();
}

class SingleRoom extends Room {
    SingleRoom(double units) { super(units); }
    String label() { return "SINGLE"; }
    double bill() { return units * 8; }                       // Rs 8 per unit
}

class SharedRoom extends Room {
    private final int occupants;

    SharedRoom(double units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    String label() { return "SHARED"; }
    double bill() { return units * 6 / occupants; }           // Rs 6 per unit, split equally
}

class AcRoom extends Room {
    AcRoom(double units) { super(units); }
    String label() { return "AC"; }
    double bill() { return units * 10 + 200; }                // Rs 10 per unit + Rs 200 fixed
}
