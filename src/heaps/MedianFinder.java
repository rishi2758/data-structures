package heaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MedianFinder {
    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;
    private int totalElements;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        totalElements = 0;
    }

    public void addNum(int num) {
        if (totalElements % 2 == 0) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        ++totalElements;
    }

    public double findMedian() {
        if(totalElements % 2 == 0) {
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MedianFinder mf = new MedianFinder();
        boolean userNotExits = true;
        do {
            Random rnd = new Random();
            mf.addNum(rnd.nextInt(50));
            System.out.println("Median :" + mf.findMedian());
            System.out.println("Press n to exit");
            String input = br.readLine();
            if (input.equalsIgnoreCase("n")) {
                userNotExits = false;
            }
        } while (userNotExits);
    }
}
