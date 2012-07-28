package org.burgers.spring.cache.namespace

import org.springframework.beans.factory.xml.BeanDefinitionParser
import org.springframework.beans.factory.config.BeanDefinition
import org.w3c.dom.Element
import org.springframework.beans.factory.xml.ParserContext
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.burgers.spring.cache.util.EntryDateTrackingCache
import org.springframework.beans.factory.parsing.BeanComponentDefinition
import org.springframework.beans.factory.support.BeanDefinitionRegistry

abstract class AbstractCacheBeanDefinitionParser implements BeanDefinitionParser {
    abstract Class getCacheClass()
    abstract String getDefaultBeanName()

    @Override
    BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(getCacheClass())

        prepareProperties(element, builder)
        prepareConstructorArgs(element, builder)

        String beanId = calculateId(element, parserContext.registry)

        parserContext.registerBeanComponent(new BeanComponentDefinition(builder.getBeanDefinition(), beanId))

        null
    }

    private String calculateId(Element element, BeanDefinitionRegistry registry) {
        String beanId = getDefaultBeanName()

        String id = element.getAttribute("id")
        if (id) {
            beanId = id
        }

        if (registry.containsBeanDefinition(beanId)) {
            beanId = beanId + "_${System.currentTimeMillis()}"
        }

        beanId
    }

    protected void prepareProperties(Element element, BeanDefinitionBuilder builder) {
    }

    protected void prepareConstructorArgs(Element element, BeanDefinitionBuilder builder) {
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
