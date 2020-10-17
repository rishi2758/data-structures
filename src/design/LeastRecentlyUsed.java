package design;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import design.DoublyLinkedList.Node;

interface LL<T>
{

    T addToFirst(T element);

    T addToLast(T element);

    boolean removeFirst(T element);

    boolean removeLast();

    T next();

    T previous();

    T getHead();

    T getTail();
}

class DoublyLinkedList<T> implements LL<T>
{

    static class Node<T>
    {
        private T node;

        private Node<T> previous;

        private Node<T> next;

        public Node(T node)
        {
            this.node = node;
        }

        public T getNode()
        {
            return node;
        }

        public Node<T> getPrevious()
        {
            return previous;
        }

        public Node<T> getNext()
        {
            return next;
        }

        public void setPrevious(Node<T> previous)
        {
            this.previous = previous;
        }

        public void setNext(Node<T> next)
        {
            this.next = next;
        }

    }

    private Node<T> head;
    private Node<T> tail;

    @Override
    public T addToFirst(T element)
    {
        Node<T> newNode = new Node<>(element);
        if (head != null) {
            Node<T> currentFirst = head.getNext();
            currentFirst.setNext(newNode);
        }
        head.setNext(newNode);
        return element;
    }

    @Override
    public T addToLast(T element)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeFirst(T element)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeLast()
    {
        
        if(head == tail) {
            throw new RuntimeException("empty list");
        }
        
        return false;
    }

    @Override
    public T next()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T previous()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getHead()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getTail()
    {
        // TODO Auto-generated method stub
        return null;
    }

}

public class LeastRecentlyUsed<K, V>
{
    private LinkedHashMap<K, Node<V>> cache;
    private final int capacity;
    private LL<V> linkedList;

    public LeastRecentlyUsed(final int capacity)
    {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
        this.linkedList = new DoublyLinkedList<>();
    }

    public void set(K key, V data)
    {
       try {
            get(key); 
       } catch (IllegalArgumentException ilae) {
       }
       if(cache.size() == capacity) {
           linkedList.removeLast();
       }
       cache.put(key, new Node<>(data));
       linkedList.addToFirst(data);
    }

    public V get(K key)
    {

        if (key != null && cache.containsKey(key)) {
            Node<V> value = cache.get(key);
            value.getPrevious().setNext(value.getNext());
            linkedList.addToFirst(value.getNode());
            return value.getNode();
        }

        throw new IllegalArgumentException("No Key Present");
    }

    public static void main(String[] args)
    {
        LeastRecentlyUsed<String, Promocodes> lru = new LeastRecentlyUsed<>(20);

    }

}

class Promocodes
{

    String promocode;

    LocalDateTime validTill;

}
