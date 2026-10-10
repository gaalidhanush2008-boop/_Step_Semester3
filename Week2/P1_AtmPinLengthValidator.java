/**
 * Week 2 - Problem 1: ATM PIN Length Validator
 * Concepts: length(), if / else, comparison operators.
 */
public class P1_AtmPinLengthValidator {

    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        checkPinLength("482");   // Invalid PIN — must be exactly 4 digits.
        checkPinLength("4820");  // PIN length OK.
    }
}
