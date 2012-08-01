package org.burgers.spring.cache.namespace

import org.burgers.spring.cache.EntryDateTrackingCache
import org.junit.Test
import org.burgers.spring.cache.TimeUnit

class DateBasedCacheBeanDefinitionParserTest extends BeanDefinitionParserTestCase {
    @Test
    void standard_configuration(){
        def myContextValue = """
                <caches:date-based-cache id="dateCache" name="words" timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
        """

        prepareContext(myContextValue)

        assert context.getBean("dateCache").class == EntryDateTrackingCache
        assert context.getBean("dateCache").timeUntilExpiration == 5
        assert context.getBean("dateCache").unitOfMeasurement == TimeUnit.MINUTES
        assert context.getBean("dateCache").name == "words"
        assert context.getBean("dateCache").allowNullValues
    }

    @Test
    void default_id(){
        def myContextValue = """
                <caches:date-based-cache name="words" timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
        """

        prepareContext(myContextValue)

        assert context.getBean("dateBasedCache").name == "words"
    }

    @Test
    void allowNullValues(){
        def myContextValue = """
                <caches:date-based-cache id="dateCache" name="words" allowNullValues="false"
                        timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
        """

        prepareContext(myContextValue)

        assert !context.getBean("dateCache").allowNullValues
    }

    @Test
    void allowNullValues_and_store(){
        def myContextValue = """
                <bean id="store" class="java.util.concurrent.ConcurrentHashMap"/>

                <caches:date-based-cache id="dateCache" name="words" allowNullValues="false" store-ref="store"
                        timeUntilExpiration="5" unitOfMeasurement="MINUTES"/>
        """

        prepareContext(myContextValue)

        EntryDateTrackingCache dateCache = context.getBean("dateCache")
        assert !dateCache.allowNullValues
        assert dateCache.getNativeCache().is(context.getBean("store"))
    }

}
