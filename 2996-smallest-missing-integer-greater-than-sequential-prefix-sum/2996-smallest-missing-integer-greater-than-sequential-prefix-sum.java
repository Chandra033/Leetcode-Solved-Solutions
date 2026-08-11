class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        for(int j = 0; j < nums.length - 1; j++) {
            if(nums[j + 1] == nums[j] + 1) {
                sum += nums[j+1];
            }
            else {
                break;
            }
        }
        while(true) {
        boolean found = false;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == sum) {
                found = true;
                break;
            } 
        }
            if(found) {
                sum++;
            } else 
                return sum;
        
        }
    }
}