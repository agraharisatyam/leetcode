class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums); // Sort in ascending order

for (int i = nums.length - 1; i >= 2; i--) {
    // Check if these 3 can form a triangle
    if (nums[i - 1] + nums[i - 2] > nums[i]) {
        // If yes, return their perimeter
        return nums[i] + nums[i - 1] + nums[i - 2];
    }
}
// If no valid triangle found
return 0;
    }
}