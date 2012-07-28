package org.burgers.spring.cache.namespace

import org.springframework.cache.concurrent.ConcurrentMapCache

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
