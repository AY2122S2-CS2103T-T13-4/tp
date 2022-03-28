package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A least recently used cache that holds a limited number of elements.
 * Adapted from https://www.geeksforgeeks.org/lru-cache-implementation/
 * @param <T>
 */
public class LruCache<T> {
    private Set<T> cache;
    private int capacity;

    /**
     * Constructs a lru cache with the given capacity.
     * @param capacity
     */
    public LruCache(int capacity) {
        requireNonNull(capacity);
        this.cache = new LinkedHashSet<T>(capacity);
        this.capacity = capacity;
    }

    /**
     * Returns an ObservableList of the cache
     * @return ObservableList of the cache
     */
    public ObservableList<T> getList() {
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

    /**
     * Adds an element to the cache. If the cache is full, remove least recently used.
     * @param key
     */
    public void put(T key) {
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

    /**
     * Add variable number of elements to the cache.
     * @param keys
     */
    @SafeVarargs
    public final void putVarArgs(T... keys) {
        for (T key : keys) {
            put(key);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LruCache<?>)) {
            return false;
        }

        // state check
        LruCache<T> e = (LruCache<T>) other;
        return capacity == e.capacity
                && cache.equals(e.cache);
    }
}
