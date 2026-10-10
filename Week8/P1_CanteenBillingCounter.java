import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 8 - Problem 1: The Canteen Billing Counter
 * Polymorphism: each customer type overrides finalAmount(); the counter never checks the type.
 *
 * Input:  N, then N lines of "STUDENT|STAFF|GUEST amount"
 */
public class P1_CanteenBillingCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();
            customers.add(createCustomer(type, amount));
        }

        double total = 0;
        for (Customer c : customers) {                    // no if-else on the type here
            double payable = c.finalAmount();
            total += payable;
            System.out.println(c.label() + ": " + String.format(Locale.US, "%.2f", payable));
        }
        System.out.println("Total: " + String.format(Locale.US, "%.2f", total));
    }

    // The only place that looks at the input text
    static Customer createCustomer(String type, double amount) {
        return switch (type) {
            case "STUDENT" -> new Student(amount);
            case "STAFF" -> new Staff(amount);
            case "GUEST" -> new Guest(amount);
            default -> throw new IllegalArgumentException("Unknown customer type: " + type);
        };
    }
}

abstract class Customer {
    protected final double amount;

    Customer(double amount) {
        this.amount = amount;
    }

    abstract String label();
    abstract double finalAmount();
}

class Student extends Customer {
    Student(double amount) { super(amount); }
    String label() { return "STUDENT"; }
    double finalAmount() { return amount * 0.90; }          // 10% discount
}

class Staff extends Customer {
    Staff(double amount) { super(amount); }
    String label() { return "STAFF"; }
    double finalAmount() { return amount * 0.95; }          // 5% discount
}

class Guest extends Customer {
    Guest(double amount) { super(amount); }
    String label() { return "GUEST"; }
    double finalAmount() { return amount + 10; }            // Rs 10 service charge
}
