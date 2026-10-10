import java.util.Arrays;

/**
 * Week 10 - Problem 8: Duty Roster Rotation
 *
 * Complexity: O(n) time, O(n) space (a new array for the result).
 * Rotating one place at a time, k times, costs O(n * k) - with k up to 10^9 that is far too
 * slow. Since n rotations bring the roster back to where it started, only k % n matters.
 */
public class P08_DutyRosterRotation {

    static String[] rotateRoster(String[] names, int k) {
        int n = names.length;
        int shift = k % n;                         // remove full turns
        String[] rotated = new String[n];

        for (int i = 0; i < n; i++) {
            rotated[(i + shift) % n] = names[i];   // name at i moves to (i + k) % n
        }
        return rotated;
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E"};
        System.out.println(Arrays.toString(rotateRoster(names, 2)));           // [D, E, A, B, C]
        System.out.println(Arrays.toString(rotateRoster(names, 7)));           // [D, E, A, B, C]
        System.out.println(Arrays.toString(rotateRoster(names, 1_000_000_000))); // [A, B, C, D, E]
    }
}
