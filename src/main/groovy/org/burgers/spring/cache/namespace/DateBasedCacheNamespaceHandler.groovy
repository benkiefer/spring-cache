package org.burgers.spring.cache.namespace

import org.springframework.beans.factory.xml.NamespaceHandlerSupport

class DateBasedCacheNamespaceHandler extends NamespaceHandlerSupport {
    void init() {
        registerBeanDefinitionParser("date-based-cache", new DateBasedCacheBeanDefinitionParser());
    }
}
