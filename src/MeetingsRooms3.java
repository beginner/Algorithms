import java.util.*;

public class MeetingsRooms3 {

    public static void main(String[] args) {
//        int[][] meetings = {
//                {0,10},
//                {1,5},
//                {2,7},
//                {3,4}
//        };
//        int n = 2;
          int[][] meetings = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        int n = 3;
        MeetingsRooms3 problem = new MeetingsRooms3();
        System.out.println(problem.mostBooked(n, meetings));


    }

//    public int mostBooked(int n, int[][] meetings) {
//        var meetingCount = new int[n];
//        var usedRooms = new PriorityQueue<long[]>((a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
//        TreeSet<Integer> unusedRooms = new TreeSet<Integer>();
//
//        for (int i = 0; i < n; i++) {
//            unusedRooms.add(i);
//        }
//
//        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
//
//        for (int[] meeting : meetings) {
//            int start = meeting[0], end = meeting[1];
//
//            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
//                int room = (int) usedRooms.poll()[1];
//                unusedRooms.add(room);
//            }
//
//            if (!unusedRooms.isEmpty()) {
//                int room = unusedRooms.first();
//                unusedRooms.remove(room);
//                usedRooms.offer(new long[]{end, room});
//                meetingCount[room]++;
//            } else {
//                long roomAvailabilityTime = usedRooms.peek()[0];
//                int room = (int) usedRooms.poll()[1];
//                usedRooms.offer(new long[]{roomAvailabilityTime + end - start, room});
//                meetingCount[room]++;
//            }
//        }
//
//        int maxMeetingCount = 0, maxMeetingCountRoom = 0;
//        for (int i = 0; i < n; i++) {
//            if (meetingCount[i] > maxMeetingCount) {
//                maxMeetingCount = meetingCount[i];
//                maxMeetingCountRoom = i;
//            }
//        }
//
//        return maxMeetingCountRoom;
//    }

    public int mostBooked(int n, int[][] meetings) {
        int[] meetingsCounter = new int[n];
        int max = 0;

        Arrays.sort(meetings, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));

        TreeSet<Integer> freeRooms = new TreeSet<>();
        for (int i = 0; i < n; i++) freeRooms.add(i);

        // 0 -> end time
        // 1 -> meeting room index
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {
            int res = Long.compare(o1[0], o2[0]);
            if (res != 0) return res;
            return Long.compare(o1[1], o2[1]);
        });


        for (int i = 0; i < meetings.length; i++) {
            int[] meeting = meetings[i];
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            while (pq.size() > 0 && pq.peek()[0] <= start) {
                freeRooms.add((int) pq.poll()[1]);
            }

            if (freeRooms.isEmpty()) {
                long[] poll = pq.poll();
                start = Math.max(start, poll[0]);
                freeRooms.add((int) poll[1]);
            }

            int room = freeRooms.first();
            meetingsCounter[room]++;
            max = Math.max(max, meetingsCounter[room]);
            freeRooms.remove(room);
            pq.offer(new long[]{start + duration, room});

        }

        for (int i = 0; i < n; i++) {
            if (max == meetingsCounter[i]) return i;
        }
        return 0;



    }

}
