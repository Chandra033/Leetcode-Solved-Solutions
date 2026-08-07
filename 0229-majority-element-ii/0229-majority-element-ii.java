class Solution {
    public List<Integer> majorityElement(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.length;
        for(int i = 0; i < n ; i++) {
            int count = 0;
            for(int j = 0; j < n; j++) {
                if(nums[i] == nums[j]) {
                    count++;
                }
            }

            if(count > n/3) {
                set.add(nums[i]);
            
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(set);

        return ans;
    }
}