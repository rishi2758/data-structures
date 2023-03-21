package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

	public int[] findOrder(int numCourses, int[][] prerequisites) {

		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

		for (int courses = 0; courses < numCourses; ++courses) {
			adjList.add(new ArrayList<>());
		}

		for (int[] prerequisite : prerequisites) {
			ArrayList<Integer> child = adjList.get(prerequisite[1]);
			child.add(prerequisite[0]);
			adjList.set(prerequisite[1], child);
		}

		int[] inDegree = new int[numCourses];
		Arrays.fill(inDegree, 0);

        for (int[] prerequisite : prerequisites) {
            ++inDegree[prerequisite[0]];
        }

		Queue<Integer> q = new LinkedList<>();
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int t = q.poll();
			ans.add(t);
			ArrayList<Integer> childrens = adjList.get(t);
			if (!childrens.isEmpty()) {
				for (Integer child : childrens) {
					--inDegree[child];
					if (inDegree[child] == 0) {
						q.add(child);
					}
				}
			}
		}

		int[] cp = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			cp[i] = ans.get(i);
		}

		return numCourses == ans.size() ? cp : new int[0];
	}

	public static void main(String[] args) {
		int[][] prereq = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		for (Integer i : new CourseScheduleII().findOrder(3, prereq)) {
			System.out.print(i + ",");
		}
	}

}
