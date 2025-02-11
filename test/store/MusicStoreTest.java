package test.store;

import src.store.MusicStore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class MusicStoreTest {
    @Test
    public void testmyTest() {
        int result = MusicStore.myTest();
        assertTrue(result == 999);
    }
}
