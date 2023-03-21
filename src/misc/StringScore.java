package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringScore {

	public void buildSegmentTree() {

	}

	private int getScore(int l, int r) {
		return -1;
	}

	private void update(int updateIndex, char replaceFrom) {

	}

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			char[] replaceFrom = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			int t = Integer.parseInt(br.readLine());
			StringScore ss = new StringScore();
			while (t-- > 0) {
				int nq = Integer.parseInt(br.readLine());
				while (nq-- > 0) {
					String[] q = br.readLine().split("//s");
					if (q[0].equals("1")) {
						System.out.println(ss.getScore(Integer.parseInt(q[1]), Integer.parseInt(q[2])));
					} else {
						ss.update(Integer.parseInt(q[1]), replaceFrom[Integer.parseInt(q[2])]);
					}
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
