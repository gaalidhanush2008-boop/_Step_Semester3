/**
 * Week 7 - Problem 3: The Password Checker
 * The password is stored privately and final, with no getter - only the strength is revealed.
 */
public class P3_PasswordChecker {

    public static void main(String[] args) {
        System.out.println(new PasswordChecker("abcd").getStrength());          // Weak
        System.out.println(new PasswordChecker("abcdefgh").getStrength());      // Medium
        System.out.println(new PasswordChecker("abcdefghij").getStrength());    // Strong
        System.out.println(new PasswordChecker("abcdefghijkl").getStrength());  // Strong
    }
}

class PasswordChecker {
    private final String password;

    PasswordChecker(String password) {
        this.password = password;
    }

    String getStrength() {
        int length = password.length();
        if (length < 6) {
            return "Weak";
        } else if (length < 10) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}
