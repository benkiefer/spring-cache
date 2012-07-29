package org.burgers.spring.cache.util

import org.burgers.spring.cache.EntryDateTrackingCache
import org.junit.Before
import org.junit.Test

class EntryDateTrackingCacheTest {
    EntryDateTrackingCache cache

    @Before
    void setUp(){
        cache = new EntryDateTrackingCache("myCache")
    }

    @Test
    void put_get_not_expire_yet() {
        cache.timeUntilExpiration = 5
        cache.unitOfMeasurement = Calendar.HOUR
        cache.put("test", "one")
        assert cache.get("test").get() == "one"
    }

    @Test
    void put_get_expire_record() {
        cache.timeUntilExpiration = -5
        cache.unitOfMeasurement = Calendar.HOUR
        cache.put("test", "one")
        assert !cache.get("test")
        assert cache.getNativeCache().isEmpty()
    }

    @Test
    void get_not_found() {
        assert !cache.get("test")
    }



}
