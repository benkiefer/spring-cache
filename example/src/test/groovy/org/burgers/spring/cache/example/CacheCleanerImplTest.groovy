package org.burgers.spring.cache.example

import org.springframework.beans.factory.annotation.Autowired
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.junit.Test
import org.springframework.cache.CacheManager
import org.springframework.cache.Cache
import org.junit.Before
import org.burgers.spring.cache.example.cleaning.CacheCleaner

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = "classpath:contexts/ApplicationContext.xml")
class CacheCleanerImplTest {
    @Autowired CacheCleaner cleaner
    @Autowired CacheManager cacheManager
    Cache cache

    @Before
    void setUp(){
        cache = cacheManager.getCache("words")
        cache.clear()
    }

    @Test
    void clean(){
        cache.put("1", "one")

        cleaner.clean()

        assert isCacheEmpty(cache)
    }

    @Test
    void cleanWithAnnotation(){
        cache.put("1", "one")

        cleaner.cleanWithAnnotations()

        assert isCacheEmpty(cache)
    }

    @Test
    void moreAdvancedCleaning(){
        cache.put("1", "one")

        cleaner.moreAdvancedCleaning()

        assert cache.get("1")

        cache.put("2", "two")
        assert !isCacheEmpty(cache)
    }

    boolean isCacheEmpty(Cache cache){
         cache.nativeCache.isEmpty()
    }

}
