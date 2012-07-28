package org.burgers.spring.cache.namespace

import org.burgers.spring.cache.util.EntryDateTrackingCache
import org.springframework.beans.MutablePropertyValues
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.config.ConstructorArgumentValues
import org.springframework.beans.factory.parsing.BeanComponentDefinition
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.beans.factory.xml.BeanDefinitionParser
import org.springframework.beans.factory.xml.ParserContext
import org.w3c.dom.Element
import org.springframework.cache.concurrent.ConcurrentMapCache

class StandardCacheBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    BeanDefinition parse(Element element, ParserContext parserContext) {
        String beanId = "standardCache"

        String id = element.getAttribute("id")

        if (id) {
            beanId = id
        } else {
            beanId = beanId + System.currentTimeMillis().toString()
        }

        RootBeanDefinition cacheBean = new RootBeanDefinition(ConcurrentMapCache.class);
        cacheBean.setConstructorArgumentValues(prepareConstructorArgs(element))

        parserContext.registerBeanComponent(new BeanComponentDefinition(cacheBean, beanId))

        null
    }

    private ConstructorArgumentValues prepareConstructorArgs(Element element) {
        ConstructorArgumentValues  values = new ConstructorArgumentValues ();
        values.addIndexedArgumentValue(0, element.getAttribute("name"))
        values
    }
}
