package misc;

import linkedlist.DoublyLinkedList;
import linkedlist.DoublyLinkedList.Node;

import java.util.HashMap;
import java.util.TreeMap;

public class LFUCache {

    private final HashMap<Integer, Node<Integer, Integer>> cache;
    private final HashMap<Integer, Integer> usedCounter;
    private final TreeMap<Integer, DoublyLinkedList<Integer, Integer>> frequentlyUsed;
    private final int capacity;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.usedCounter = new HashMap<>();
        this.frequentlyUsed = new TreeMap<>();
        this.capacity = capacity;
    }

    public void get(int key) {
        if (cache.isEmpty() || !cache.containsKey(key)) {
            return;
        }

        DoublyLinkedList.Node<Integer, Integer> node = cache.get(key);

        int usedCount = usedCounter.get(key) + 1;
        DoublyLinkedList dll = frequentlyUsed.getOrDefault(usedCount, new DoublyLinkedList<>());
        dll.addLast(key, node.getValue());
        frequentlyUsed.put(usedCount, dll);
        usedCounter.put(key, usedCount + 1);

        DoublyLinkedList.Node previous = node.getPrevious();
        DoublyLinkedList.Node next = node.getNext();
        previous.setNext(next);
        next.setPrev(previous);

        node.getValue();
    }

    public void put(int key, int value) {
        if (cache.size() > capacity) {
            //TODO: remove the entry if all the values of doubly linked list are removed.
            frequentlyUsed.firstEntry().getValue().removeFirst();
            cache.remove(key);
            usedCounter.remove(key);
        }

        DoublyLinkedList.Node<Integer, Integer> node = cache.getOrDefault(key, null);
        if (node == null) {
            node = new DoublyLinkedList.Node<>(key, value);
            cache.put(key, node);
        } else {
            DoublyLinkedList.Node previous = node.getPrevious();
            DoublyLinkedList.Node next = node.getNext();
            previous.setNext(next);
            next.setPrev(previous);
        }

        int usedCount = usedCounter.getOrDefault(key, 0);
        usedCounter.put(key, usedCount + 1);
        DoublyLinkedList<Integer, Integer> dll = frequentlyUsed.getOrDefault(usedCount + 1, new DoublyLinkedList<>());
        cache.put(key, dll.addLast(key, value));
        frequentlyUsed.put(usedCount + 1, dll);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(5);
        cache.put(100, 1);
        cache.put(101, 2);
        cache.put(103, 3);
        cache.get(100);
        cache.put(101, 2);
        cache.get(101);
        cache.put(105, 5);
        cache.get(105);
        cache.put(100, 1);
        cache.get(100);
    }
}
