/**
 * Week 6 - M2: Payroll Salary Management
 * basicSalary and bonus are private - there is no public way to set them directly.
 */
public class M2_PayrollSalaryManagement {

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());

        // Rejected inputs
        account.creditBonus(-100);
        account.deductTax(150);
        new PayrollAccount(-1);
    }
}

class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double openingBasicSalary) {
        if (openingBasicSalary < 0) {
            System.out.println("Warning: negative salary not allowed, starting at 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = openingBasicSalary;
        }
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid bonus amount: must be greater than 0.");
            return;
        }
        bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percent: must be between 0 and 100.");
            return;
        }
        basicSalary -= basicSalary * percent / 100;
        // Print 10 instead of 10.0 for whole percentages
        String shown = (percent == Math.floor(percent)) ? String.valueOf((long) percent) : String.valueOf(percent);
        System.out.println("Tax deducted: " + shown + "%");
    }

    public double getNetSalary() {
        return basicSalary + bonus;
    }
}
