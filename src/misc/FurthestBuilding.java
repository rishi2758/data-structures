package misc;

public class FurthestBuilding {

	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		return _furthestBuilding(heights, 1, bricks, ladders);
	}

	private int _furthestBuilding(int[] heights, int i, int bricks, int ladders) {

		// [4,2,7,6,9,14,12]
		// [0,-2,5,-1,3,5,-2]
		// count = 3 || sum = 13
		// count < 3
		// [0,-2,0,-1,3,5,-2] b- 5 , l - 0 or b - 0 , l - 1

		if (i == heights.length) {
			return 0;
		}

		int ans = 0;
		int cost = heights[i] - heights[i - 1];
		if (cost < 0) {
			ans = 1 + _furthestBuilding(heights, i + 1, bricks, ladders);
		} else {
			int choice1 = 0;
			int choice2 = 0;
			if (bricks > 0 && cost <= bricks) {
				choice1 = 1 + _furthestBuilding(heights, i + 1, bricks - cost, ladders);
			}
			if (ladders > 0) {
				choice2 = 1 + _furthestBuilding(heights, i + 1, bricks, ladders - 1);
			}
			ans = Math.max(choice1, choice2);
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] heights = { 4, 12, 2, 7, 3, 18, 20, 3, 19 };
		System.out.println(new FurthestBuilding().furthestBuilding(heights, 10, 2));
	}

}
