package org.burgers.spring.cache.util

import org.junit.Test
import org.junit.Before
import org.burgers.spring.cache.EntryDateTrackingCache

class EntryDateTrackingCacheTest {
    EntryDateTrackingCache cache

    @Before
    void setUp(){
        cache = new EntryDateTrackingCache("myCache")
    }

    @Test
    void put_get() {
        cache.timeUntilExpiration = 5
        cache.unitOfMeasurement = Calendar.HOUR
        cache.put("test", "one")
        assert cache.get("test").get() == "one"
    }

    @Test
    void clearExpiredRecords() {
        cache.timeUntilExpiration = -5
        cache.unitOfMeasurement = Calendar.HOUR
        cache.put("test", "one")
        cache.clearExpiredRecords()
        assert cache.getNativeCache().isEmpty()
    }

    @Test
    void clearExpiredRecords_not_old_enough() {
        cache.timeUntilExpiration = 5
        cache.unitOfMeasurement = Calendar.HOUR
        cache.put("test", "one")
        cache.clearExpiredRecords()
        assert cache.getNativeCache().size() == 1
    }

    @Test
    void get_not_found() {
        assert !cache.get("test")
    }



}
