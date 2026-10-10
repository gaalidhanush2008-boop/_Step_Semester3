/**
 * Week 10 - Problem 4: Exam Score Band Counter
 *
 * Count of scores in [low, high] = (first index with score > high) - (first index with score >= low).
 * Both positions come from a modified binary search that keeps going after a match,
 * so duplicates at the edges of the band are handled correctly.
 *
 * Complexity:
 *   Linear scan : O(n) time per query, O(1) space.
 *   This solution: O(log n) time per query (two binary searches), O(1) space.
 *
 * Duplicates: a normal binary search stops at ANY matching element, which could be the middle
 * of a run of equal scores. We need the FIRST position of the run, so when we find a match we
 * keep searching to the left instead of stopping.
 */
public class P04_ExamScoreBandCounter {

    /** First index whose value is >= target (scores.length if there is none). */
    private static int firstIndexAtLeast(int[] scores, int target) {
        int lo = 0;
        int hi = scores.length;          // search in [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (scores[mid] >= target) {
                hi = mid;                // could be the first one - keep looking left
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    static int countInBand(int[] scores, int low, int high) {
        int start = firstIndexAtLeast(scores, low);       // first score >= low
        int end = firstIndexAtLeast(scores, high + 1);    // first score > high (scores are whole numbers)
        return end - start;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));   // 6
        System.out.println(countInBand(scores, 90, 100));  // 0
    }
}
