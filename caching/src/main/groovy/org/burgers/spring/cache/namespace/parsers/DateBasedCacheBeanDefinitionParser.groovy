package org.burgers.spring.cache.namespace.parsers

import org.burgers.spring.cache.namespace.builders.CacheBeanDefinitionBuilder
import org.burgers.spring.cache.namespace.builders.DateBasedCacheBeanDefinitionBuilder


class DateBasedCacheBeanDefinitionParser extends AbstractCacheBeanDefinitionParser {
    @Override
    CacheBeanDefinitionBuilder getBuilder() {
        return new DateBasedCacheBeanDefinitionBuilder()
    }
}
