import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 8 - Problem 4: The Festival Bonus Calculator
 * Name and salary are common to all employees (parent class); the bonus rule differs per type.
 *
 * Input:  N, then N lines of "FULLTIME|PARTTIME|INTERN name monthlySalary"
 */
public class P4_FestivalBonusCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<StaffMember> staff = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();
            staff.add(createStaffMember(type, name, salary));
        }

        double total = 0;
        for (StaffMember s : staff) {
            double bonus = s.bonus();
            total += bonus;
            System.out.println(s.getName() + ": " + String.format(Locale.US, "%.2f", bonus));
        }
        System.out.println("Total Bonus: " + String.format(Locale.US, "%.2f", total));
    }

    static StaffMember createStaffMember(String type, String name, double salary) {
        return switch (type) {
            case "FULLTIME" -> new FullTimeEmployee(name, salary);
            case "PARTTIME" -> new PartTimeEmployee(name, salary);
            case "INTERN" -> new InternEmployee(name, salary);
            default -> throw new IllegalArgumentException("Unknown employee type: " + type);
        };
    }
}

abstract class StaffMember {
    private final String name;
    protected final double monthlySalary;

    StaffMember(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    String getName() { return name; }

    abstract double bonus();
}

class FullTimeEmployee extends StaffMember {
    FullTimeEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return monthlySalary * 0.10; }           // 10% of salary
}

class PartTimeEmployee extends StaffMember {
    PartTimeEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return monthlySalary * 0.05; }           // 5% of salary
}

class InternEmployee extends StaffMember {
    InternEmployee(String name, double salary) { super(name, salary); }
    double bonus() { return 2000; }                           // fixed Rs 2,000
}
