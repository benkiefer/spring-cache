package org.burgers.spring.cache.namespace

import org.junit.After
import org.junit.Before
import org.springframework.context.ApplicationContext
import org.springframework.context.support.FileSystemXmlApplicationContext

abstract class BeanDefinitionParserTestCase {
    ApplicationContext context
    File file

    @Before
    void setUp() {
        file = File.createTempFile("test", ".txt")
    }

    @After
    void tearDown() {
        file.delete()
    }

    protected void prepareContext(String myValue) {
        def contextContent = """\
               <beans xmlns="http://www.springframework.org/schema/beans"
                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                      xmlns:caches="http://www.burgers.org/schema/cache"
                      xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                      http://www.burgers.org/schema/cache http://www.burgers.org/schema/cache.xsd">

                <bean id="customEditorConfigurer" class="org.springframework.beans.factory.config.CustomEditorConfigurer">
                      <property name="customEditors">
                            <map>
                              <entry key="org.burgers.spring.cache.TimeUnit">
                                <bean class="org.burgers.spring.cache.TimeUnitPropertyEditor"/>
                              </entry>
                            </map>
                      </property>
                </bean>

                ${myValue}

               </beans>
          """
        file.text = contextContent
        context = new FileSystemXmlApplicationContext(file.absolutePath)
    }
}

