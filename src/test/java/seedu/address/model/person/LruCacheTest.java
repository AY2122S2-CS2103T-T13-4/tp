package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class LruCacheTest {
    @Test
    public void getList_works() {
        LruCache<Integer> cacheInteger = new LruCache<Integer>(3); // [3 4 2]
        cacheInteger.putVarArgs(1, 2, 3, 4, 3);

        ObservableList<Integer> observableList = FXCollections.observableArrayList();
        observableList.addAll(3, 4, 2);

        assertEquals(observableList, cacheInteger.getList());
    }

    @Test
    public void put_works() {
        LruCache<Integer> cacheInteger = new LruCache<Integer>(3); // [3 4 2]
        cacheInteger.putVarArgs(1, 2, 3, 4, 3);

        LruCache<Integer> equivalentStateCache = new LruCache<Integer>(3); // [3 4 2]
        equivalentStateCache.putVarArgs(2, 4, 3);

        assertEquals(equivalentStateCache, cacheInteger);
    }

    @Test
    public void equals() {
        LruCache<Integer> cacheInteger = new LruCache<Integer>(3);
        LruCache<String> cacheString = new LruCache<String>(3);
        cacheInteger.put(1);
        cacheString.put("String");

        // same object -> returns true
        assertTrue(cacheInteger.equals(cacheInteger));

        // same values -> returns true
        LruCache<Integer> cacheIntegerCopy = new LruCache<Integer>(3);
        cacheIntegerCopy.put(1);
        assertTrue(cacheInteger.equals(cacheIntegerCopy));

        // different types -> returns false
        assertFalse(cacheInteger.equals(1));

        // null -> returns false
        assertFalse(cacheInteger.equals(null));

        // different person -> returns false
        assertFalse(cacheInteger.equals(cacheString));
    }
}
