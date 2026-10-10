/**
 * Week 10 - Problem 7: Second-Best Score
 *
 * Complexity: O(n) time (one pass), O(1) space (two variables).
 * Sorting first would cost O(n log n) time (and O(n) extra space for some sorts), and the
 * array would still need a pass to skip duplicates of the maximum - so one pass is better.
 */
public class P07_SecondBestScore {

    static int secondHighest(int[] scores) {
        int highest = -1;
        int second = -1;                 // -1 means "no second-highest found yet" (scores are >= 0)

        for (int score : scores) {
            if (score > highest) {
                second = highest;        // old highest becomes second
                highest = score;
            } else if (score < highest && score > second) {
                second = score;          // equal to highest is skipped
            }
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(secondHighest(new int[]{45, 78, 92, 78, 60})); // 78
        System.out.println(secondHighest(new int[]{50, 50, 50}));         // -1
        System.out.println(secondHighest(new int[]{0}));                  // -1
        System.out.println(secondHighest(new int[]{0, 100}));             // 0
    }
}
