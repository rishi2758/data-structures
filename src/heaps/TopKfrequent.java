package heaps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TopKfrequent
{
    class Element {
        int num;
        int freq;
    }

    public int[] topKFrequent(int[] nums , int k) {
        
        Map<Integer,Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 1)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>(new EntryComparator());
        heap.addAll(freqMap.entrySet());
        int[] kFrequent = new int[k];
        for (int i = 0; i < k; i++) {
            kFrequent[i] = heap.poll().getKey();
        }
        return kFrequent;
    }
    
    class EntryComparator implements Comparator<Map.Entry<Integer,Integer>> {

        @Override
        public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)
        {
            return o1.getValue().compareTo(o2.getValue());
        }
        
    }
    
    public static void main(String[] args)
    {
        
    }
    
}
