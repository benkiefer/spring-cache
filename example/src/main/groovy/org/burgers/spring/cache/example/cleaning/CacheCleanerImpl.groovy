package org.burgers.spring.cache.example.cleaning

import org.burgers.spring.cache.EntryDateTrackingCache
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Component

@Component
class CacheCleanerImpl implements CacheCleaner {
    @Autowired
    CacheManager cacheManager


//    example of how to evict the cache manually
    void clean(){
        cacheManager.getCache("words").clear()
    }



//    example of how to use the Spring annotations to clean the cache.
    @CacheEvict (value = "words", allEntries=true)
    void cleanWithAnnotations() { }



//    clean a big cache.
    @CacheEvict (value = "words", condition="caches[0].getNativeCache().size() > 1")
    void moreAdvancedCleaning() { }



//    create special type of cache that can be used to purge old records.
    void cleanExpiredRecords() {
        EntryDateTrackingCache cache = (EntryDateTrackingCache) cacheManager.getCache("words")
        cache.clearExpiredRecords()
    }
}
