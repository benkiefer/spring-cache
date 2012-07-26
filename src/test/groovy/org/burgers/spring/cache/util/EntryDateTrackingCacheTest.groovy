package org.burgers.spring.cache.util

import org.junit.Test
import org.junit.Before

class EntryDateTrackingCacheTest {
    EntryDateTrackingCache cache

    @Before
    void setUp(){
        cache = new EntryDateTrackingCache("myCache")
    }

    @Test
    void put_get() {
        cache.put("test", "one")
        assert cache.get("test").get() == "one"
    }

    @Test
    void deleteBefore() {
        cache.put("test", "one")
        Date date = new Date().plus(1)
        cache.clearBefore(date)
        assert cache.getNativeCache().isEmpty()
    }

    @Test
    void deleteBefore_not_old_enough() {
        cache.put("test", "one")
        Date date = new Date().minus(1)
        cache.clearBefore(date)
        assert cache.getNativeCache().size() == 1
    }

    @Test
    void get_not_found() {
        assert !cache.get("test")
    }



}
