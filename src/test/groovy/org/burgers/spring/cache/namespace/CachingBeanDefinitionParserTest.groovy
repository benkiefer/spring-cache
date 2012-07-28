package org.burgers.spring.cache.namespace

import org.springframework.cache.support.SimpleCacheManager
import org.junit.Test

class CachingBeanDefinitionParserTest extends BeanDefinitionParserTestCase {
    @Test
    void default_id(){
        def myContextValue = """
                <caches:caching>
                    <caches:default id="cache1" name="myCache"/>
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
                    <caches:default id="cache1" name="myCache"/>
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
                    <caches:default id="cache1" name="myCache"/>
                    <caches:default id="cache2" name="myOtherCache"/>
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
                    <caches:default id="cache1" name="myCache"/>
                    <caches:date-based-cache id="cache2" name="myOtherCache"
                            timeUntilExpiration="5" unitOfMeasurement="12"/>
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
                    <caches:date-based-cache id="cache2" name="myOtherCache"
                            timeUntilExpiration="5" unitOfMeasurement="12"/>
                    <caches:default id="cache1" name="myCache"/>
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
