package org.burgers.spring.cache

import org.springframework.beans.factory.annotation.Autowired
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.junit.Test
import org.springframework.cache.CacheManager
import org.springframework.cache.Cache
import org.springframework.beans.factory.annotation.Qualifier
import org.burgers.spring.cache.example.cleaning.CacheCleaner;

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = "classpath:contexts/ApplicationContext.xml")
class WordCacheCleanerImplTest {
    @Autowired
    @Qualifier("wordCacheCleaner")
    CacheCleaner cleaner
    @Autowired CacheManager cacheManager

    @Test
    void clean(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.clean()

        assert !cache.get("1")
    }

    @Test
    void cleanWithAnnotation(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.cleanWithAnnotations()

        assert !cache.get("1")
    }

    @Test
    void moreAdvancedCleaning(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.moreAdvancedCleaning()

        assert cache.get("1")

        cache.put("2", "two")
        assertCacheIsEmpty(cache)
    }

    void assertCacheIsEmpty(Cache cache){
         cache.nativeCache.isEmpty()
    }

}
