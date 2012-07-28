package org.burgers.spring.cache.namespace

import org.junit.Test
import org.springframework.cache.concurrent.ConcurrentMapCache

class StandardCacheBeanDefinitionParserTest extends BeanDefinitionParserTestCase{
    @Test
    void standard_configuration(){
        def myContextValue = """
                <caches:default id="myCache" name="words"/>
        """

        prepareContext(myContextValue)

        assert context.getBean("myCache").class == ConcurrentMapCache
        assert context.getBean("myCache").name == "words"
        assert context.getBean("myCache").allowNullValues
    }

    @Test
    void default_id(){
        def myContextValue = """
                <caches:default name="words"/>
        """

        prepareContext(myContextValue)

        assert context.getBean("standardCache").name == "words"
    }

    @Test
    void allowNullValues(){
        def myContextValue = """
                <caches:default id="myCache" name="words" allowNullValues="false"/>
        """

        prepareContext(myContextValue)

        assert !context.getBean("myCache").allowNullValues
    }

    @Test
    void allowNullValues_and_store(){
        def myContextValue = """
                <bean id="store" class="java.util.concurrent.ConcurrentHashMap"/>

                <caches:default id="myCache" name="words" allowNullValues="false" store-ref="store"/>
        """

        prepareContext(myContextValue)

        ConcurrentMapCache cache = context.getBean("myCache")
        assert !cache.allowNullValues
        assert cache.getNativeCache().is(context.getBean("store"))
    }

}
