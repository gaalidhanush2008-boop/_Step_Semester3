/**
 * Week 4 - A5: Find Minimum in Rotated Sorted Array
 * Modified binary search: compare the middle element with the rightmost element.
 * O(log n) time, O(1) space.
 */
public class A5_FindMinInRotatedSortedArray {

    static int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;      // minimum is in the right half (mid is not it)
            } else {
                high = mid;         // minimum is at mid or in the left half
            }
        }
        return nums[low];           // also handles the no-rotation case
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));        // 1
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));  // 0
        System.out.println(findMin(new int[]{11, 13, 15, 17}));       // 11
    }
}
