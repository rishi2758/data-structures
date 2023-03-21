package stack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class Deque<T> implements java.util.Deque<T>
{
    private final Stack<T> frontStack;
    private final Stack<T> backStack;

    public Deque()
    {
        frontStack = new Stack<>();
        backStack = new Stack<>();
    }
    
    @Override
    public boolean addAll(Collection<? extends T> c)
    {
        return frontStack.addAll(c);
    }

    @Override
    public void clear()
    {
        frontStack.clear();
        backStack.clear();
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return new HashSet<>(frontStack).containsAll(c) || new HashSet<>(backStack).containsAll(c);
    }

    @Override
    public boolean isEmpty()
    {
        return frontStack.isEmpty() && backStack.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(T e)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addFirst(T e)
    {
        frontStack.push(e);
    }

    @Override
    public void addLast(T e)
    {
        backStack.push(e);
    }

    @Override
    public boolean contains(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<T> descendingIterator()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T element()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getFirst()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T getLast()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<T> iterator()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean offer(T e)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean offerFirst(T e)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean offerLast(T e)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T peek()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T peekFirst()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T peekLast()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T poll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T pollFirst()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T pollLast()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T pop()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void push(T e)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T remove()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T removeFirst()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T removeLast()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeLastOccurrence(Object o)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size()
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
