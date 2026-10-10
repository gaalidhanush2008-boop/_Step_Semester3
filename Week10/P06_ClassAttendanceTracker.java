/**
 * Week 10 - Problem 6: Class Attendance Tracker
 *
 * Complexity: O(n) time, O(1) space. Yes - both answers come from a single pass:
 * count every 1, and keep a running streak that resets on every 0.
 */
public class P06_ClassAttendanceTracker {

    static String attendanceSummary(int[] days) {
        int present = 0;
        int streak = 0;
        int longest = 0;

        for (int day : days) {
            if (day == 1) {
                present++;
                streak++;
                if (streak > longest) {
                    longest = streak;
                }
            } else {
                streak = 0;
            }
        }
        return "Present: " + present + ", Longest streak: " + longest;
    }

    public static void main(String[] args) {
        System.out.println(attendanceSummary(new int[]{1, 1, 0, 1, 1, 1, 0, 1})); // Present: 6, Longest streak: 3
        System.out.println(attendanceSummary(new int[]{0, 0, 0}));                // Present: 0, Longest streak: 0
    }
}
