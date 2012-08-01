package org.burgers.spring.cache.namespace

import org.junit.Test
import org.springframework.cache.support.SimpleCacheManager

class CachingBeanDefinitionParserTest extends BeanDefinitionParserTestCase {
    @Test
    void default_id(){
        def myContextValue = """
                <caches:caching>
                    <caches:standard id="cache1" name="myCache"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("cacheManager")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
    }

    @Test
    void default_custom_id(){
        def myContextValue = """
                <caches:caching id="custom">
                    <caches:standard id="cache1" name="myCache"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("custom")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
    }

    @Test
    void default_multiple_caches(){
        def myContextValue = """
                <caches:caching>
                    <caches:standard id="cache1" name="myCache"/>
                    <caches:standard id="cache2" name="myOtherCache"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("cacheManager")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCacheNames().contains("myOtherCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
        assert manager.getCache("myOtherCache").is(context.getBean("cache2"))
    }

    @Test
    void multiple_types_of_caches(){
        def myContextValue = """
                <caches:caching>
                    <caches:standard id="cache1" name="myCache"/>
                    <caches:date-based-cache id="cache2" name="myOtherCache"
                            timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("cacheManager")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCacheNames().contains("myOtherCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
        assert manager.getCache("myOtherCache").is(context.getBean("cache2"))
    }

    @Test
    void multiple_types_of_caches_order_doesnt_matter(){
        def myContextValue = """
                <caches:caching>
                    <caches:standard id="cache1" name="myCache"/>
                    <caches:date-based-cache id="cache2" name="myOtherCache"
                            timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("cacheManager")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCacheNames().contains("myOtherCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
        assert manager.getCache("myOtherCache").is(context.getBean("cache2"))
    }

    @Test
    void cache_ref_hook(){
        def myContextValue = """
                <caches:standard id="cache1" name="myCache"/>

                <caches:caching>
                    <caches:cache-ref ref="cache1"/>
                    <caches:date-based-cache id="cache2" name="myOtherCache"
                            timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
                </caches:caching>
        """

        prepareContext(myContextValue)

        SimpleCacheManager manager = context.getBean("cacheManager")
        assert manager.getCacheNames().contains("myCache")
        assert manager.getCacheNames().contains("myOtherCache")
        assert manager.getCache("myCache").is(context.getBean("cache1"))
        assert manager.getCache("myOtherCache").is(context.getBean("cache2"))
    }

}
