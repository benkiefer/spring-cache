package org.burgers.spring.cache.namespace

import org.burgers.spring.cache.util.EntryDateTrackingCache
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.support.FileSystemXmlApplicationContext
import org.springframework.cache.concurrent.ConcurrentMapCache

class StandardCacheBeanDefinitionParserTest {
    ApplicationContext context
    File file

    @Before
    void setUp(){
        file = File.createTempFile("context", ".txt")
    }

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

    @After
    void tearDown(){
        file.delete()
    }

    private void prepareContext(String myValue){
        def contextContent = """\
             <beans xmlns="http://www.springframework.org/schema/beans"
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:caches="http://www.burgers.org/schema/cache"
                    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                    http://www.burgers.org/schema/cache http://www.burgers.org/schema/cache.xsd">

              ${myValue}

             </beans>
        """
        file.text = contextContent
        context = new FileSystemXmlApplicationContext(file.absolutePath)
    }
}
