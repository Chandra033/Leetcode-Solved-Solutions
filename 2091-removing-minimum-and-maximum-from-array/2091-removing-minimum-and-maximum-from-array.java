class Solution {
    public int minimumDeletions(int[] nums) {
        int maxIndex = 0;
        int minIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }  if(nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
          int n = nums.length;

        // Both from left
        int left = Math.max(minIndex, maxIndex) + 1;

        // Both from right
        int right = n - Math.min(minIndex, maxIndex);

        // One from left and one from right
        int both = Math.min(minIndex, maxIndex) + 1
                 + n - Math.max(minIndex, maxIndex);

        return Math.min(left, Math.min(right, both));
    }
}