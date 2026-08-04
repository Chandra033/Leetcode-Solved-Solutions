class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int small = Integer.MAX_VALUE;
        int large = Integer.MIN_VALUE;
        List<Integer> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();        
      for(int i = 0; i < nums.length; i++) {
        set.add(nums[i]);
            if(nums[i] < small) {
                small = nums[i];
                
            }
      
            if(nums[i] > large) {
                large = nums[i];
            }
      }
            for(int j = small; j <= large; j++) {
                if(!set.contains(j)) {
                    ans.add(j);
                }
            }
        
        return ans;
    }
}