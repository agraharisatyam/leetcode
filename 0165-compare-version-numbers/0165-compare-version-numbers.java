class Solution {
    public int compareVersion(String version1, String version2) {
        // Step 1: Split version strings
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // Step 2: Find the longest length
        int maxLen = Math.max(v1.length, v2.length);

        // Step 3: Loop and compare each part
        for (int i = 0; i < maxLen; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }

        // Step 4: If all parts are equal
        return 0;
    }
}