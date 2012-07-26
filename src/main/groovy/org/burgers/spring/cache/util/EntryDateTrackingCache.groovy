package org.burgers.spring.cache.util

import org.springframework.cache.concurrent.ConcurrentMapCache
import java.util.concurrent.ConcurrentMap
import org.springframework.cache.support.SimpleValueWrapper
import org.springframework.cache.Cache.ValueWrapper

class EntryDateTrackingCache extends ConcurrentMapCache {
    EntryDateTrackingCache(String name) {
        super(name)
    }

    EntryDateTrackingCache(String name, boolean allowNullValues) {
        super(name, allowNullValues)
    }

    EntryDateTrackingCache(String name, ConcurrentMap<Object, Object> store, boolean allowNullValues) {
        super(name, store, allowNullValues)
    }

    @Override
    void put(Object key, Object value) {
        super.put(key, new DateStampedValue(realValue: value))
    }

    void clearAnythingOlderThan(int minutes){
        Date expirationDate = getExpirationDate(minutes)

        def map = getNativeCache()

        map.each{key, DateStampedValue value ->
            if (value.date.before(expirationDate))
                map.remove(key)
        }
    }

    private Date getExpirationDate(int minutes) {
        def calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -minutes)
        calendar.getTime()
    }

    @Override
    ValueWrapper get(Object key) {
        ValueWrapper value = super.get(key)
        if (value){
            DateStampedValue wrappedValue = value.get()
            return new SimpleValueWrapper(wrappedValue.realValue)
        }
        value
    }

    class DateStampedValue {
        def realValue
        Date date = new Date()
    }

}
