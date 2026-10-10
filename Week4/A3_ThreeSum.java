import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Week 4 - A3: 3Sum
 * Sort, then for each element use two pointers on the remaining part.
 * Duplicates are skipped at all three levels. O(n^2) time.
 */
public class A3_ThreeSum {

    static int[][] threeSum(int[] nums) {
        int[] a = nums.clone();   // do not modify the caller's array
        Arrays.sort(a);
        List<int[]> result = new ArrayList<>();
        int n = a.length;

        for (int i = 0; i < n - 2; i++) {
            if (a[i] > 0) break;                           // smallest value already positive
            if (i > 0 && a[i] == a[i - 1]) continue;       // skip duplicate first value

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = a[i] + a[left] + a[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(new int[]{a[i], a[left], a[right]});
                    left++;
                    right--;
                    while (left < right && a[left] == a[left - 1]) left++;     // skip duplicate second value
                    while (left < right && a[right] == a[right + 1]) right--;  // skip duplicate third value
                }
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(threeSum(new int[]{-1, 0, 1, 2, -1, -4}))); // [[-1, -1, 2], [-1, 0, 1]]
        System.out.println(Arrays.deepToString(threeSum(new int[]{0, 0, 0})));             // [[0, 0, 0]]
        System.out.println(Arrays.deepToString(threeSum(new int[]{-2, -2, 0, 0, 2, 2})));  // [[-2, 0, 2]]
    }
}
