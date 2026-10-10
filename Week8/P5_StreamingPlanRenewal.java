import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Week 8 - Problem 5: The Streaming Plan Renewal Reminder
 * Each plan knows its own validity; LocalDate.plusDays() handles leap years and year changes.
 *
 * Input:  N, then N lines of "BASIC|STANDARD|PREMIUM name YYYY-MM-DD"
 */
public class P5_StreamingPlanRenewal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String plan = sc.next();
            String name = sc.next();
            LocalDate start = LocalDate.parse(sc.next());
            subscribers.add(new Subscriber(name, start, createPlan(plan)));
        }

        for (Subscriber s : subscribers) {
            System.out.println(s.name + ": " + s.renewalDate());
        }
    }

    static Plan createPlan(String type) {
        return switch (type) {
            case "BASIC" -> new BasicPlan();
            case "STANDARD" -> new StandardPlan();
            case "PREMIUM" -> new PremiumPlan();
            default -> throw new IllegalArgumentException("Unknown plan: " + type);
        };
    }
}

abstract class Plan {
    abstract int validityDays();

    LocalDate renewalDate(LocalDate start) {
        return start.plusDays(validityDays());
    }
}

class BasicPlan extends Plan {
    int validityDays() { return 30; }
}

class StandardPlan extends Plan {
    int validityDays() { return 90; }
}

class PremiumPlan extends Plan {
    int validityDays() { return 365; }
}

class Subscriber {
    final String name;
    private final LocalDate startDate;
    private final Plan plan;

    Subscriber(String name, LocalDate startDate, Plan plan) {
        this.name = name;
        this.startDate = startDate;
        this.plan = plan;
    }

    LocalDate renewalDate() {
        return plan.renewalDate(startDate);
    }
}
