class Solution {
    public int maxFreeTime(int ev, int[] s, int[] e) {
        // ev: Total time available in the day (e.g., 37 hours)
        // s: Array of meeting start times (e.g., [5, 14, 27, 34])
        // e: Array of meeting end times (e.g., [13, 18, 31, 37])

        int last = 0; // The end time of the last meeting we've seen (starts at 0 because the day begins at time 0)
        int n = s.length; // The number of meetings (same as the number of elements in startTime array)
        int left = 0; // The maximum free time we've seen so far *to the left* of a given meeting (starts at 0)
        int res = 0; // The result: the maximum free time we find (starts at 0)

        int[] right = new int[n + 1]; // Array to store maximum free time to the *right* of a meeting (plus one for the end of day)
        int[] gaps = new int[n + 1]; // Array to store gaps (free time) between meetings (plus one for the end of day)

        // Calculate gaps (free time) between meetings
        for (int i = 0; i < n; i++) {
            gaps[i] = s[i] - last; // The gap between the current meeting's start and the previous meeting's end
            last = e[i]; // Update the last meeting's end time
        }

        gaps[n] = ev - last; // Calculate the gap at the end of the day

        // Calculate maximum free time to the *right* of each meeting
        for (int i = n - 1; i >= 0; i--)
            right[i] = Math.max(right[i + 1], gaps[i + 1]); // The maximum of: 1) the max free time to the right of the *next* meeting, and 2) the gap *between* the current and next meeting.

        // Loop through meetings, trying to find the best time to move one meeting
        for (int i = 1; i <= n; i++) {
            int dur = e[i - 1] - s[i - 1]; // Calculate the duration of the *current* meeting

            // If we can "fit" this meeting into the existing gaps (either to the left or right), update the result
            if (left >= dur || right[i] >= dur)
                res = Math.max(res, gaps[i - 1] + dur + gaps[i]); // Add the duration to the gaps around the meeting

            left = Math.max(left, gaps[i - 1]); // update left.
            res = Math.max(res, gaps[i - 1] + gaps[i]); // What if we just *removed* this meeting?  Update result based on that
        }

        return res; // Return the maximum free time
    }
}