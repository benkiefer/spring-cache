package org.burgers.spring.cache.namespace.parsers

import org.burgers.spring.cache.util.EntryDateTrackingCache
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.w3c.dom.Element
import org.burgers.spring.cache.namespace.builders.CacheBeanDefinitionBuilder
import org.burgers.spring.cache.namespace.builders.DateBasedCacheBeanDefinitionBuilder

class DateBasedCacheBeanDefinitionParser extends AbstractCacheBeanDefinitionParser {
    @Override
    CacheBeanDefinitionBuilder getBuilder() {
        return new DateBasedCacheBeanDefinitionBuilder()
    }
}
