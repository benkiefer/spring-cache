package org.burgers.spring.cache.namespace.builders

import org.springframework.cache.concurrent.ConcurrentMapCache

class StandardCacheBeanDefinitionBuilder extends CacheBeanDefinitionBuilder {
    @Override
    Class getBeanClass() {
        ConcurrentMapCache.class
    }

    @Override
    String getDefaultBeanName() {
        "standardCache"
    }
}
