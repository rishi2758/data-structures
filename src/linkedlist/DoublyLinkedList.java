package linkedlist;

public class DoublyLinkedList<K, V> {

    public static class Node<K, V> {
        private final V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.value = value;
        }

        public Node<K, V> getPrevious() {
            return this.prev;
        }

        public Node<K, V> getNext() {
            return this.next;
        }

        public void setPrev(Node<K, V> prev) {
            this.prev = prev;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public V getValue() {
            return value;
        }
    }

    private Node<K, V> head;
    private Node<K, V> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public Node<K, V> addLast(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail.prev;
            tail = tail.next;
        }
        return node;
    }

    public Node<K, V> addFirst(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head.next;
            head = head.prev;
        }
        return node;
    }

    @SuppressWarnings("unchecked")
    public void removeFirst() {
        if (head == null) {
            return;
        }

        Node<K, V> tmp = head.next;
        head = tmp.next;
        tmp.next = null;
        head.prev = null;
    }

    @SuppressWarnings("unchecked")
    public void removeLast() {
        if (tail == null) {
            return;
        }

        Node<K, V> tmp = tail.prev;
        tail = tmp.prev;
        tmp.prev = null;
    }
}
