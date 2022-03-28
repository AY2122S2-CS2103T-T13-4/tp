package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class UniqueCommentListTest {
    private final UniqueCommentList uniqueCommentList = new UniqueCommentList();
    private List<Comment> arr = Stream.of("a", "b", "c", "d", "e").map(Comment::new).collect(Collectors.toList());
    private Comment stub = new Comment("a");

    @Test
    public void contains_nullComment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommentList.contains(null));
    }

    @Test
    public void contains_commentNotInList_returnsFalse() {
        assertFalse(uniqueCommentList.contains(stub));
    }

    @Test
    public void contains_commentInList_returnsTrue() {
        uniqueCommentList.addToCache(stub);
        assertTrue(uniqueCommentList.contains(stub));
    }

    @Test
    public void add_nullComment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCommentList.addToCache(null));
    }

    @Test
    public void add_duplicateComment_returnsTrue() {
        uniqueCommentList.addToCache(stub);
        uniqueCommentList.addToCache(stub);
        uniqueCommentList.addToCache(stub);
        assertEquals(1, uniqueCommentList.asUnmodifiableObservableList().size());
    }

    @Test
    public void set_comments_returnsTrue() {
        uniqueCommentList.setComments(arr);

        UniqueCommentList expectedCommentList = new UniqueCommentList();
        expectedCommentList.setComments(arr);

        assertEquals(uniqueCommentList, expectedCommentList);
    }
}
