package org.burgers.spring.cache.namespace.parsers

import org.springframework.cache.concurrent.ConcurrentMapCache
import org.burgers.spring.cache.namespace.builders.CacheBeanDefinitionBuilder
import org.burgers.spring.cache.namespace.builders.StandardCacheBeanDefinitionBuilder

class StandardCacheBeanDefinitionParser extends AbstractCacheBeanDefinitionParser {
    @Override
    CacheBeanDefinitionBuilder getBuilder() {
        return new StandardCacheBeanDefinitionBuilder()
    }
}
