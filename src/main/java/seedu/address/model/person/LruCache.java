package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LruCache<T> {
    private Set<T> cache;
    private int capacity;

    public LruCache(int capacity) {
        this.cache = new LinkedHashSet<T>(capacity);
        this.capacity = capacity;
    }

    // This function returns false if key is not
    // present in cache. Else it moves the key to
    // front by first removing it and then adding
    // it, and returns true.
    public boolean get(T key) {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }

    // displays contents of cache in Reverse Order
    public ObservableList<T> getList()
    {
        LinkedList<T> list = new LinkedList<>(cache);

        // The descendingIterator() method of java.util.LinkedList
        // class is used to return an iterator over the elements
        // in this LinkedList in reverse sequential order
        Iterator<T> itr = list.descendingIterator();

        ObservableList<T> observableList = FXCollections.observableArrayList();
        while (itr.hasNext()) {
            observableList.add(itr.next());
        }

        return observableList;
    }

    public void put(T key)
    {
        // if exists move to front
        if (cache.contains(key)) {
            cache.remove(key);
            cache.add(key);
            return;
        }

        // else check for size
        if (cache.size() == capacity) {
            T firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }
}
