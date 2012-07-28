package org.burgers.spring.cache.namespace.builders

import org.burgers.spring.cache.namespace.util.BeanIdCalculator
import org.springframework.beans.factory.config.BeanDefinition

import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.xml.BeanDefinitionParser
import org.springframework.beans.factory.xml.ParserContext
import org.w3c.dom.Element

abstract class CacheBeanDefinitionBuilder implements BeanDefinitionParser {
    BeanIdCalculator calculator = new BeanIdCalculator()
    abstract Class getBeanClass()
    abstract String getDefaultBeanName()

    BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(getBeanClass())

        prepareProperties(element, builder)
        prepareConstructorArgs(element, builder)

        builder.getBeanDefinition()
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
