package org.burgers.spring.cache.namespace

import org.springframework.context.ApplicationContext
import org.junit.Test
import org.junit.Before
import org.springframework.context.support.FileSystemXmlApplicationContext
import org.junit.After
import org.burgers.spring.cache.util.EntryDateTrackingCache

class DateBasedCacheBeanDefinitionParserTest {
    ApplicationContext context
    File file
    @Before
    void setUp(){
        file = File.createTempFile("context", ".txt")
    }


    @Test
    void standard_configuration(){
        def myValue = """
                <caches:date-based-cache id="dateCache" name="words" timeUntilExpiration="5" unitOfMeasurement="12"/>
        """

        prepareContext(myValue)
        assert context.getBean("dateCache").class == EntryDateTrackingCache
        assert context.getBean("dateCache").timeUntilExpiration == 5
        assert context.getBean("dateCache").unitOfMeasurement == 12
        assert context.getBean("dateCache").name == "words"
    }

    @After
    void tearDown(){
        file.delete()
    }

    void prepareContext(String myValue){
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
