package greedy;

import java.util.Arrays;

/**
 * <a href="https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1">N Meetings in One Room</a>
 * You are given timings of n meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and end[i] is the finish time of meeting i.
 * Return the maximum number of meetings that can be accommodated in a single meeting room, when only one meeting can be held in the meeting room at a particular time.
 * Note: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
 */
public class NMeetingsInOneRoom {
    private static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private Meeting[] createMeetings(int[] start, int[] end) {
        ;
        Meeting[] meetings = new Meeting[start.length];
        for (int i = 0; i < start.length; i++) {
            meetings[i] = new Meeting(start[i], end[i]);
        }
        return meetings;
    }

    // Time Complex: O(n log n) + O(n) where n is the number of meetings, we are sorting the meetings based on their end time
    // Space Complex: O(n) where n is the number of meetings, we are creating an array of meetings to store the start and end time of each meeting
    // We can also do it without the extra space by sorting the start and end time arrays separately and then using two pointers to find the maximum number of meetings that can be accommodated in a single meeting room.
    private int optimal(int[] start, int[] end) {
        Meeting[] meetings = createMeetings(start, end);
        // Sort the meetings in ASC order of their end time
        Arrays.sort(meetings, (a, b) -> a.end - b.end);
        Meeting currentMeeting = meetings[0];
        int meetingCount = 1;
        for (int i = 1; i < meetings.length; i++) {
            // We cannot use >= here because the start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
            // Constraint: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
            if (meetings[i].start >= currentMeeting.end) {
                meetingCount++;
                currentMeeting = meetings[i];
            }
        }
        return meetingCount;
    }

    public int maxMeetings(int[] start, int[] end) {
        // add your code here
        return optimal(start, end);
    }

    public static void main(String[] args) {
        NMeetingsInOneRoom nmio = new NMeetingsInOneRoom();
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        int res = nmio.maxMeetings(start, end);
        System.out.println(res);
    }
}
