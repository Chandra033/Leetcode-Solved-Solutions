class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int left = 0;
        int maxlen = 0;
        int[] freq = new int[256];
        for(int i = 0; i < ch.length; i++) {
            freq[ch[i]]++;
            while(freq[ch[i]] > 1) {
                freq[ch[left]]--;
                left++;
            }
            int len = i - left + 1;
            if(len > maxlen) {
                maxlen = len;
            }
        }
        return maxlen;
    }
}
//sliding window and frequency array concepts used.