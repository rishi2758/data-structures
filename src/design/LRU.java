package design;

import java.util.HashMap;

class Node
{
    final int key;

    int value;

    Node pre;

    Node next;

    public Node(int key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

class LRUCache
{
    private final HashMap<Integer, Node> map;

    private final int capicity;
    private int count;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity)
    {
        this.capicity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }

    public void deleteNode(Node node)
    {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(Node node)
    {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    // This method works in O(1)
    public int get(int key)
    {
        if (map.get(key) != null) {
            Node node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            System.out.println("Got the value : " + result + " for the key: " + key);
            return result;
        }
        System.out.println("Did not get any value" + " for the key: " + key);
        return -1;
    }

    // This method works in O(1)
    public void set(int key, int value)
    {
        System.out.println("Going to set the (key, " + "value) : (" + key + ", " + value + ")");
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if (count < capicity) {
                count++;
                addToHead(node);
            } else {
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }
}
