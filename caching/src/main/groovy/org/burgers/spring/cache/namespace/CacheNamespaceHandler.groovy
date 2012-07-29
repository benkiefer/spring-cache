package org.burgers.spring.cache.namespace

import org.burgers.spring.cache.namespace.parsers.CachingBeanDefinitionParser
import org.burgers.spring.cache.namespace.parsers.DateBasedCacheBeanDefinitionParser
import org.burgers.spring.cache.namespace.parsers.StandardCacheBeanDefinitionParser
import org.springframework.beans.factory.xml.NamespaceHandlerSupport

class CacheNamespaceHandler extends NamespaceHandlerSupport {
    void init() {
        registerBeanDefinitionParser("caching", new CachingBeanDefinitionParser());
        registerBeanDefinitionParser("standard", new StandardCacheBeanDefinitionParser());
        registerBeanDefinitionParser("date-based-cache", new DateBasedCacheBeanDefinitionParser());
    }
}
