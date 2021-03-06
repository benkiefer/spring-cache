package org.burgers.spring.cache.namespace.parsers

import org.burgers.spring.cache.namespace.builders.CacheBeanDefinitionBuilder
import org.burgers.spring.cache.namespace.util.BeanIdCalculator
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.parsing.BeanComponentDefinition
import org.springframework.beans.factory.xml.BeanDefinitionParser
import org.springframework.beans.factory.xml.ParserContext
import org.w3c.dom.Element

abstract class AbstractCacheBeanDefinitionParser implements BeanDefinitionParser {
    BeanIdCalculator calculator = new BeanIdCalculator()

    abstract CacheBeanDefinitionBuilder getBuilder()

    BeanDefinition parse(Element element, ParserContext parserContext) {
        String beanId = calculator.calculate(builder.defaultBeanName, element, parserContext.registry)

        def beanDefinition = builder.parse(element, parserContext)
        parserContext.registerBeanComponent(new BeanComponentDefinition(beanDefinition, beanId))
        null
    }
}
