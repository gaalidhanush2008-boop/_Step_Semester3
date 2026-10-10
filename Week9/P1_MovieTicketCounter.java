import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 9 - Problem 1: Movie Ticket Counter
 * Abstract class: the convenience fee and the amount formula are written once in Ticket;
 * each seat type only supplies its own price. Ticket is abstract, so a plain "ticket"
 * with no seat type can never be created.
 *
 * Input:  N, then N lines of "REGULAR|PREMIUM|RECLINER count"
 */
public class P1_MovieTicketCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Ticket> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();
            bookings.add(createTicket(seat, count));
        }

        double total = 0;
        for (Ticket t : bookings) {
            double amount = t.amount();
            total += amount;
            System.out.println(t.seatName() + ": " + String.format(Locale.US, "%.2f", amount));
        }
        System.out.println("Total: " + String.format(Locale.US, "%.2f", total));
    }

    static Ticket createTicket(String seat, int count) {
        return switch (seat) {
            case "REGULAR" -> new RegularTicket(count);
            case "PREMIUM" -> new PremiumTicket(count);
            case "RECLINER" -> new ReclinerTicket(count);
            default -> throw new IllegalArgumentException("Unknown seat type: " + seat);
        };
    }
}

abstract class Ticket {
    private static final double CONVENIENCE_FEE = 20;   // the one and only place for the fee
    private final int count;

    Ticket(int count) {
        this.count = count;
    }

    abstract String seatName();
    abstract double seatPrice();

    final double amount() {
        return count * (seatPrice() + CONVENIENCE_FEE);
    }
}

class RegularTicket extends Ticket {
    RegularTicket(int count) { super(count); }
    String seatName() { return "REGULAR"; }
    double seatPrice() { return 150; }
}

class PremiumTicket extends Ticket {
    PremiumTicket(int count) { super(count); }
    String seatName() { return "PREMIUM"; }
    double seatPrice() { return 250; }
}

class ReclinerTicket extends Ticket {
    ReclinerTicket(int count) { super(count); }
    String seatName() { return "RECLINER"; }
    double seatPrice() { return 400; }
}
