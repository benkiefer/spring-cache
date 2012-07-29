package org.burgers.spring.cache.namespace.parsers

import org.w3c.dom.Element
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.xml.ParserContext

import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.util.xml.DomUtils
import org.springframework.beans.factory.parsing.BeanComponentDefinition
import org.springframework.beans.factory.xml.BeanDefinitionParser
import org.burgers.spring.cache.namespace.util.BeanIdCalculator
import org.springframework.beans.factory.support.ManagedSet
import org.springframework.beans.BeanMetadataAttribute
import org.burgers.spring.cache.namespace.builders.DateBasedCacheBeanDefinitionBuilder
import org.burgers.spring.cache.namespace.builders.StandardCacheBeanDefinitionBuilder
import org.springframework.beans.factory.config.RuntimeBeanNameReference
import org.springframework.beans.factory.config.RuntimeBeanReference

class CachingBeanDefinitionParser implements BeanDefinitionParser {
    DateBasedCacheBeanDefinitionBuilder dateCacheBuilder = new DateBasedCacheBeanDefinitionBuilder()
    StandardCacheBeanDefinitionBuilder standardCacheBuilder = new StandardCacheBeanDefinitionBuilder()
    BeanIdCalculator calculator = new BeanIdCalculator()

    @Override
    BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SimpleCacheManager)

        def beanId = calculator.calculate("cacheManager", element, parserContext.registry)

        prepareCaches(element, builder, parserContext)

        parserContext.registerBeanComponent(new BeanComponentDefinition(builder.getBeanDefinition(), beanId))
        builder.getBeanDefinition()
    }

    private void prepareCaches(Element element, BeanDefinitionBuilder builder, ParserContext parserContext) {
        ManagedSet set = new ManagedSet()

        DomUtils.getChildElementsByTagName(element, "standard").each {
            def cache = standardCacheBuilder.parse(it, parserContext)
            String beanName = calculator.calculate(standardCacheBuilder.getDefaultBeanName(), it, parserContext.registry)
            parserContext.registerBeanComponent(new BeanComponentDefinition(cache, beanName))
            set.add(new RuntimeBeanReference(beanName))
        }

        DomUtils.getChildElementsByTagName(element, "date-based-cache").each {
            def cache = dateCacheBuilder.parse(it, parserContext)
            String beanName = calculator.calculate(dateCacheBuilder.getDefaultBeanName(), it, parserContext.registry)
            parserContext.registerBeanComponent(new BeanComponentDefinition(cache, beanName))
            set.add(new RuntimeBeanReference(beanName))
        }

        DomUtils.getChildElementsByTagName(element, "cache-ref").each {
            set.add(new RuntimeBeanReference(it.getAttribute("ref")))
        }

        builder.addPropertyValue("caches", set)
    }

}
