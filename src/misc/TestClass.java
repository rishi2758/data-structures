package misc;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import for Scanner and other utility classes
import java.util.HashMap;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

@SuppressWarnings("LoopConditionNotUpdatedInsideLoop")
class TestClass {

	static class Pair {
		private final int key;
		private final String value;

		public Pair(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return this.key;
		}

		public String getValue() {
			return this.value;
		}

	}

	public static void main(String[] args) throws Exception {
		/*
		 * Sample code to perform I/O: Use either of these methods for input
		 *
		 * //BufferedReader BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in)); String name = br.readLine(); // Reading input
		 * from STDIN System.out.println("Hi, " + name + "."); // Writing output to
		 * STDOUT
		 *
		 * //Scanner Scanner s = new Scanner(System.in); String name = s.nextLine(); //
		 * Reading input from STDIN System.out.println("Hi, " + name + "."); // Writing
		 * output to STDOUT
		 *
		 */

		// Write your code here
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] inputArr = br.readLine().split(" ");
		HashMap<String, Integer> idx = new HashMap<>();
		for (int i = 0; i < n; i++) {
			idx.put(inputArr[i], i);
		}
		int m = Integer.parseInt(br.readLine());

		while (m > 0) {
			String[] queryArr = br.readLine().split(" ");
			String a = queryArr[0];
			String b = queryArr[1];

			int aidx = idx.get(a);
			int bidx = idx.get(b);

			if (aidx + 1 < bidx + 1) {
				// move right
			} else {
				// move left
				for (int mv = aidx + 1; mv > bidx + 1; mv--) {
					String temp = inputArr[mv];
					inputArr[mv] = inputArr[mv - 1];
					inputArr[mv - 1] = temp;
				}

			}

			System.out.println(String.join(" ", inputArr));
		}

	}
}
