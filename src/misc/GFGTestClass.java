package misc;

public class GFGTestClass {

	public static void swap(Integer i, Integer j) {
		Integer temp = i;
		i = j;
		j = temp;
	}

	public static void _swap(Integer i, Integer j) {
		Integer temp = i;
		i = j;
		j = temp;
	}

	public static void main(String[] args) {
		Integer i = 10;
		Integer j = 20;
		swap(i, j);
		System.out.println("i = " + i + ", j = " + j);

		_swap(i, j);
		System.out.println("i = " + i + ", j = " + j);
	}

}
