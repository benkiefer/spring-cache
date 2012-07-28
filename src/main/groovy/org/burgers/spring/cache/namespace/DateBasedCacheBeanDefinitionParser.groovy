package org.burgers.spring.cache.namespace

import org.w3c.dom.Element
import org.burgers.spring.cache.util.EntryDateTrackingCache
import org.springframework.beans.factory.support.BeanDefinitionBuilder

class DateBasedCacheBeanDefinitionParser extends AbstractCacheBeanDefinitionParser {
    @Override
    Class getCacheClass() {
        EntryDateTrackingCache.class
    }

    @Override
    String getDefaultBeanName() {
        "dateBasedCache"
    }

    @Override
    protected void prepareProperties(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("timeUntilExpiration", element.getAttribute("timeUntilExpiration"))
        builder.addPropertyValue("unitOfMeasurement", element.getAttribute("unitOfMeasurement"))
    }
}
