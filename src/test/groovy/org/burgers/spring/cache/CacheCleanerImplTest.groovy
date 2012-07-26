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
class CacheCleanerImplTest {
    @Autowired
    CacheCleaner cleaner
    @Autowired CacheManager cacheManager

    @Test
    void clean(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.clean()

        assert isCacheEmpty(cache)
    }

    @Test
    void cleanWithAnnotation(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.cleanWithAnnotations()

        assert isCacheEmpty(cache)
    }

    @Test
    void moreAdvancedCleaning(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.moreAdvancedCleaning()

        assert cache.get("1")

        cache.put("2", "two")
        assert !isCacheEmpty(cache)
    }

    @Test
    void dateBasedCleaning(){
        def cache = cacheManager.getCache("words")
        cache.put("1", "one")

        cleaner.dateBasedCleaning()

        assert cache.get("1").get() == "one"

        cleaner.minutesInCache = 0
        cleaner.dateBasedCleaning()

        assert isCacheEmpty(cache)
    }

    boolean isCacheEmpty(Cache cache){
         cache.nativeCache.isEmpty()
    }

}
