package org.burgers.spring.cache.example.cleaning

import org.springframework.stereotype.Component
import org.springframework.cache.CacheManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict

@Component("wordCacheCleaner")
class WordCacheCleanerImpl implements CacheCleaner {
    @Autowired
    CacheManager cacheManager

//    example of how to evict the cache manually
    void clean(){
        cacheManager.getCache("words").clear()
    }

//    example of how to use the Spring annotations to clean the cache.
    @CacheEvict (value = "words", allEntries=true)
    void cleanWithAnnotations() { }

    @CacheEvict (value = "words", condition="caches[0].getNativeCache().size() > 1")
    void moreAdvancedCleaning() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
