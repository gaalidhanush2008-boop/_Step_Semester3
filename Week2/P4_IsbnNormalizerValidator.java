/**
 * Week 2 - Problem 4: Library ISBN Normalizer & Validator
 * Valid code = 3 letters (publisher) + 4 digits (year) + 6 digits (catalog) = 13 chars.
 * Concepts: trim(), substring(), Character.isLetter()/isDigit(), StringBuilder.
 */
public class P4_IsbnNormalizerValidator {

    /** Trims the code and upper-cases only the first 3 characters. */
    static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /** Validates a normalized code and returns the display line or the reason it is invalid. */
    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: year and catalog number must be digits";
            }
        }

        StringBuilder line = new StringBuilder();
        line.append("[").append(code, 0, 3).append("] ")
            .append("YEAR: ").append(code, 3, 7)
            .append(" | CATALOG: ").append(code.substring(7));
        return line.toString();
    }

    public static void main(String[] args) {
        System.out.println(validateAndFormat(normalizeCode(" pen2026004251 "))); // [PEN] YEAR: 2026 | CATALOG: 004251
        System.out.println(validateAndFormat(normalizeCode("12N2026004251")));   // Invalid: publisher code must be 3 letters
        System.out.println(validateAndFormat(normalizeCode("pen202600425")));    // Invalid: code must be exactly 13 characters
        System.out.println(validateAndFormat(normalizeCode("pen2026A04251")));   // Invalid: year and catalog number must be digits
    }
}
