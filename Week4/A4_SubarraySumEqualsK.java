import java.util.HashMap;
import java.util.Map;

/**
 * Week 4 - A4: Subarray Sum Equals K
 * Running prefix sum + hash map of prefix-sum frequencies. O(n) time, O(n) space.
 *
 * Why not a sliding window? With negative numbers, extending the window can make the sum
 * smaller and shrinking it can make the sum larger, so you cannot decide which end to move.
 */
public class A4_SubarraySumEqualsK {

    static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);      // the "empty prefix" - lets subarrays starting at index 0 count

        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            count += prefixCount.getOrDefault(sum - k, 0);
            prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));   // 2
        System.out.println(subarraySum(new int[]{1, -1, 0}, 0));  // 3
    }
}
