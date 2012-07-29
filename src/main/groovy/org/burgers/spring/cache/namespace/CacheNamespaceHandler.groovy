package org.burgers.spring.cache.namespace

import org.springframework.beans.factory.xml.NamespaceHandlerSupport
import org.burgers.spring.cache.namespace.parsers.CachingBeanDefinitionParser
import org.burgers.spring.cache.namespace.parsers.DateBasedCacheBeanDefinitionParser
import org.burgers.spring.cache.namespace.parsers.StandardCacheBeanDefinitionParser

class CacheNamespaceHandler extends NamespaceHandlerSupport {
    void init() {
        registerBeanDefinitionParser("caching", new CachingBeanDefinitionParser());
        registerBeanDefinitionParser("date-based-cache", new DateBasedCacheBeanDefinitionParser());
        registerBeanDefinitionParser("standard", new StandardCacheBeanDefinitionParser());
    }
}
