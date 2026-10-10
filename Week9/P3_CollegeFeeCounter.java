import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 9 - Problem 3: College Fee Counter
 * Every student has a name and a tuition rule (abstract class). Only some students use the
 * bus, so "uses the bus" is an interface (UsesBus) that holds the transport fee in one place.
 * The counter asks "is this student a UsesBus?" - it never checks the student type by name.
 *
 * Input:  N, then N lines of "DAY_SCHOLAR|HOSTELLER|SCHOLAR name"
 */
public class P3_CollegeFeeCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            students.add(createStudent(type, name));
        }

        double total = 0;
        for (Student s : students) {
            double fee = s.tuition();
            if (s instanceof UsesBus) {
                fee += UsesBus.TRANSPORT_FEE;
            }
            total += fee;
            System.out.println(s.getName() + ": " + String.format(Locale.US, "%.2f", fee));
        }
        System.out.println("Total Collected: " + String.format(Locale.US, "%.2f", total));
    }

    static Student createStudent(String type, String name) {
        return switch (type) {
            case "DAY_SCHOLAR" -> new DayScholar(name);
            case "HOSTELLER" -> new Hosteller(name);
            case "SCHOLAR" -> new ScholarshipStudent(name);
            default -> throw new IllegalArgumentException("Unknown student type: " + type);
        };
    }
}

interface UsesBus {
    double TRANSPORT_FEE = 12000;      // the one place the transport fee is written
}

abstract class Student {
    private final String name;

    Student(String name) {
        this.name = name;
    }

    String getName() { return name; }

    abstract double tuition();
}

class DayScholar extends Student implements UsesBus {
    DayScholar(String name) { super(name); }
    double tuition() { return 40000; }
}

class Hosteller extends Student {                       // lives on campus: no bus
    Hosteller(String name) { super(name); }
    double tuition() { return 40000 + 60000; }          // tuition + hostel fee
}

class ScholarshipStudent extends Student implements UsesBus {
    ScholarshipStudent(String name) { super(name); }
    double tuition() { return 40000 / 2.0; }            // half of the normal tuition
}
