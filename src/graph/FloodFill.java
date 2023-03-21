package graph;

public class FloodFill {

	public void floodFill(int[][] image, int sr, int sc, int newColor) {

		if (image == null || sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length) {
			return;
		}

		floodFillUtil(image, sr, sc, newColor, image[sr][sc]);

    }

	private void floodFillUtil(int[][] image, int sr, int sc, int newColor, int prevColor) {
		if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != prevColor
				|| prevColor == newColor) {
			return;
		}

		prevColor = image[sr][sc];
		image[sr][sc] = newColor;

		floodFillUtil(image, sr, sc - 1, newColor, prevColor);
		floodFillUtil(image, sr - 1, sc, newColor, prevColor);
		floodFillUtil(image, sr + 1, sc, newColor, prevColor);
		floodFillUtil(image, sr, sc + 1, newColor, prevColor);

	}

	public static void main(String[] args) {
		int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		new FloodFill().floodFill(image, 1, 1, 2);
	}

}
