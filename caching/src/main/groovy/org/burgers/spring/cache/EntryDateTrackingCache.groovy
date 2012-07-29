package org.burgers.spring.cache

import org.springframework.cache.Cache
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleValueWrapper

import java.util.concurrent.ConcurrentMap

class EntryDateTrackingCache extends ConcurrentMapCache {
    private final ConcurrentMap<Object, Object> store;
    int timeUntilExpiration
    int unitOfMeasurement

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
        super.put(key, new DateStampedValue(value, calculateExpirationDate()))
    }

    private Date calculateExpirationDate() {
        def calendar = Calendar.getInstance()
        calendar.add(unitOfMeasurement, -timeUntilExpiration)
        calendar.getTime()
    }

    @Override
    Cache.ValueWrapper get(Object key) {
        DateStampedValue value = super.get(key)?.get()
        if (value){
            if (new Date().after(value.expirationDate)) {
                return new SimpleValueWrapper(value.realValue)
            } else {
                super.evict(key)
            }
        }
        null
    }

    class DateStampedValue {
        def realValue
        Date expirationDate

        DateStampedValue(value, Date expirationDate) {
            this.expirationDate = expirationDate
            this.realValue = value
        }

    }

}


