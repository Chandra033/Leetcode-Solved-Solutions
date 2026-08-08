class Solution {
    public int[] validSequence(String word1, String word2) {

        int m = word2.length();

        // last[j] = index in word1 where word2[j] can be matched
        // while keeping the remaining suffix possible
        int[] last = new int[m];

        for (int i = 0; i < m; i++) {
            last[i] = -1;
        }

        int i = word1.length() - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        i = 0;
        j = 0;

        boolean changed = false;

        while (i < word1.length() && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }
            else if (!changed &&
                     (j == m - 1 || i < last[j + 1])) {

                // Use our one allowed mismatch
                ans[j] = i;
                j++;
                changed = true;
            }

            i++;
        }

        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}