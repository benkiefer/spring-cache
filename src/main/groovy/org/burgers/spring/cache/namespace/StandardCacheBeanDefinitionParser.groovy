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

class StandardCacheBeanDefinitionParser extends AbstractCacheBeanDefinitionParser {
    @Override
    Class getCacheClass() {
        ConcurrentMapCache.class
    }

    @Override
    String getDefaultBeanName() {
        "standardCache"
    }
}
