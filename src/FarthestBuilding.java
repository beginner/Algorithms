public class FarthestBuilding {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        return recurse(0, heights, bricks, ladders);
    }

    private int recurse(int pos, int[] heights, int bricks, int ladders) {
        if (pos == heights.length - 1) return pos;

        int diff = heights[pos + 1] - heights[pos];
        if (diff <= 0) {
            return recurse(pos + 1, heights, bricks, ladders);
        } else {
            int usingBricks = Integer.MIN_VALUE;
            if (bricks - diff >= 0) {
                usingBricks = recurse(pos + 1, heights, bricks - diff, ladders);
            }
            int usingLadders = Integer.MIN_VALUE;
            if (ladders > 0) {
                usingLadders = recurse(pos + 1, heights, bricks, ladders - 1);
            }
            int max = Math.max(usingBricks, usingLadders);
            if (max == Integer.MIN_VALUE) return pos;
            else return max;


        }


    }

    public static void main(String[] args) {
        int index = 2;
        char c = (char)('a' + index);
        System.out.println(c);
    }

}
