import java.util.*;

class Solution {

    static class Pair {
        Map<Integer, Integer> map;
        boolean ok;

        Pair(Map<Integer, Integer> map, boolean ok) {
            this.map = map;
            this.ok = ok;
        }
    }

    private static final Map<Integer, Map<Integer, Integer>> FACTOR_COUNTS =
            Map.of(
                    0, Map.of(),
                    1, Map.of(),
                    2, Map.of(2, 1),
                    3, Map.of(3, 1),
                    4, Map.of(2, 2),
                    5, Map.of(5, 1),
                    6, Map.of(2, 1, 3, 1),
                    7, Map.of(7, 1),
                    8, Map.of(2, 3),
                    9, Map.of(3, 2));

    public String smallestNumber(String num, long t) {

        Pair res = getPrimeCount(t);
        Map<Integer, Integer> primeCount = res.map;

        if (!res.ok)
            return "-1";

        Map<Integer, Integer> factorCount = getFactorCount(primeCount);

        if (sumValues(factorCount) > num.length())
            return construct(factorCount);

        Map<Integer, Integer> prefix = getPrimeCount(num);

        int firstZero = num.indexOf('0');

        if (firstZero == -1) {
            firstZero = num.length();
            if (isSubset(primeCount, prefix))
                return num;
        }

        for (int i = num.length() - 1; i >= 0; i--) {

            int d = num.charAt(i) - '0';

            prefix = subtract(prefix, FACTOR_COUNTS.get(d));

            int remaining = num.length() - 1 - i;

            if (i > firstZero)
                continue;

            for (int nd = d + 1; nd <= 9; nd++) {

                Map<Integer, Integer> need = getFactorCount(
                        subtract(
                                subtract(primeCount, prefix),
                                FACTOR_COUNTS.get(nd)));

                if (sumValues(need) <= remaining) {

                    int ones = remaining - sumValues(need);

                    return num.substring(0, i)
                            + nd
                            + "1".repeat(ones)
                            + construct(need);
                }
            }
        }

        Map<Integer, Integer> need = getFactorCount(primeCount);

        return "1".repeat(num.length() + 1 - sumValues(need))
                + construct(need);
    }

    private Pair getPrimeCount(long t) {

        Map<Integer, Integer> count =
                new HashMap<>(Map.of(2, 0, 3, 0, 5, 0, 7, 0));

        for (int p : new int[]{2, 3, 5, 7}) {
            while (t % p == 0) {
                t /= p;
                count.put(p, count.get(p) + 1);
            }
        }

        return new Pair(count, t == 1);
    }

    private Map<Integer, Integer> getPrimeCount(String num) {

        Map<Integer, Integer> count =
                new HashMap<>(Map.of(2, 0, 3, 0, 5, 0, 7, 0));

        for (char c : num.toCharArray()) {
            for (Map.Entry<Integer, Integer> e :
                    FACTOR_COUNTS.get(c - '0').entrySet()) {
                count.merge(e.getKey(), e.getValue(), Integer::sum);
            }
        }

        return count;
    }

    private Map<Integer, Integer> getFactorCount(Map<Integer, Integer> count) {

        int c8 = count.get(2) / 3;
        int rem2 = count.get(2) % 3;

        int c9 = count.get(3) / 2;
        int c3 = count.get(3) % 2;

        int c4 = rem2 / 2;
        int c2 = rem2 % 2;

        int c6 = 0;

        if (c2 == 1 && c3 == 1) {
            c2 = 0;
            c3 = 0;
            c6 = 1;
        }

        if (c3 == 1 && c4 == 1) {
            c2 = 1;
            c6 = 1;
            c3 = 0;
            c4 = 0;
        }

        return Map.of(
                2, c2,
                3, c3,
                4, c4,
                5, count.get(5),
                6, c6,
                7, count.get(7),
                8, c8,
                9, c9);
    }

    private String construct(Map<Integer, Integer> count) {

        StringBuilder sb = new StringBuilder();

        for (int d = 2; d <= 9; d++) {
            sb.append(String.valueOf(d).repeat(count.get(d)));
        }

        return sb.toString();
    }

    private boolean isSubset(Map<Integer, Integer> a,
                             Map<Integer, Integer> b) {

        for (int k : a.keySet()) {
            if (b.get(k) < a.get(k))
                return false;
        }

        return true;
    }

    private Map<Integer, Integer> subtract(Map<Integer, Integer> a,
                                           Map<Integer, Integer> b) {

        Map<Integer, Integer> res = new HashMap<>(a);

        for (Map.Entry<Integer, Integer> e : b.entrySet()) {
            res.put(e.getKey(),
                    Math.max(0, res.get(e.getKey()) - e.getValue()));
        }

        return res;
    }

    private int sumValues(Map<Integer, Integer> map) {

        int sum = 0;

        for (int x : map.values())
            sum += x;

        return sum;
    }
}