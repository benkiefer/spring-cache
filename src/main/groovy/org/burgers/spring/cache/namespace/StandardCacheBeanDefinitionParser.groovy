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
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry

class StandardCacheBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ConcurrentMapCache.class)

        prepareConstructorArgs(element, builder)

        String beanId = calculateId(element, parserContext.registry)

        parserContext.registerBeanComponent(new BeanComponentDefinition(builder.getBeanDefinition(), beanId))

        null
    }

    private String calculateId(Element element, BeanDefinitionRegistry registry) {
        String beanId = "standardCache"

        String id = element.getAttribute("id")
        if (id) {
            beanId = id
        }

        if (registry.containsBeanDefinition(beanId)){
            beanId = beanId + "_${System.currentTimeMillis()}"
        }

        beanId
    }

    private void prepareConstructorArgs(Element element, BeanDefinitionBuilder builder) {
        builder.addConstructorArg(element.getAttribute("name"))

        def allowNullValues = element.getAttribute("allowNullValues")
        def store = element.getAttribute("store-ref")
        if (allowNullValues && store) {
            builder.addConstructorArgReference(store)
            builder.addConstructorArg(allowNullValues)
        } else if (allowNullValues && !store) {
            builder.addConstructorArg(allowNullValues)
        }
    }

}
