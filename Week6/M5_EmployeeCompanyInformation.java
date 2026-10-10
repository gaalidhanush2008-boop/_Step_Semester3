/**
 * Week 6 - M5: Employee and Company Information Management
 * companyName and employeeCount are static: one shared copy for every employee.
 */
public class M5_EmployeeCompanyInformation {

    public static void main(String[] args) {
        new Employee("Asha", 50000);
        new Employee("Ravi", 42000);
        new Employee("Neha", 38000);

        Employee.printCompanyInfo();   // called through the class name, not an object
    }
}

class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    // Static method: uses only static fields, no instance fields
    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}
