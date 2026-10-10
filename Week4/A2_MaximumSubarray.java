/**
 * Week 4 - A2: Maximum Subarray (Kadane's algorithm)
 * At each element: extend the running subarray, or restart from the current element.
 * O(n) time, O(1) extra space.
 *
 * Follow-up (divide and conquer, O(n log n)): split the array at the middle; the best
 * subarray is either fully in the left half, fully in the right half, or crosses the
 * middle (best suffix of the left half + best prefix of the right half).
 */
public class A2_MaximumSubarray {

    static int maxSubArray(int[] nums) {
        int current = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]); // restart vs. extend
            best = Math.max(best, current);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        System.out.println(maxSubArray(new int[]{-3, -1, -2}));                    // -1
    }
}
