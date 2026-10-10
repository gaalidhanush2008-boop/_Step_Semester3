/**
 * Week 6 - M3: Employee Profile Creation
 * Two constructors; the intern constructor chains to the 3-argument one with this(...).
 */
public class M3_EmployeeProfileCreation {

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);
        Employee intern = new Employee("E-102", "Arjun");

        permanent.printProfile(); // E-101 | Divya | Rs 65000.0 | Intern: false
        intern.printProfile();    // E-102 | Arjun | Rs 0.0 | Intern: true
    }
}

class Employee {
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    // Permanent employee
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    // Intern: chain to the constructor above with salary 0, then flag as intern
    public Employee(String empId, String empName) {
        this(empId, empName, 0);
        this.isIntern = true;
    }

    void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }
}
