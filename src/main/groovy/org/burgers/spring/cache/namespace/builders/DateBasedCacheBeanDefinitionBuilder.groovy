package org.burgers.spring.cache.namespace.builders

import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.w3c.dom.Element
import org.burgers.spring.cache.EntryDateTrackingCache

class DateBasedCacheBeanDefinitionBuilder extends CacheBeanDefinitionBuilder {

    @Override
    Class getBeanClass() {
        EntryDateTrackingCache
    }

    @Override
    String getDefaultBeanName() {
        return "dateBasedCache"
    }

    @Override
    protected void prepareProperties(Element element, BeanDefinitionBuilder builder) {
        builder.addPropertyValue("timeUntilExpiration", element.getAttribute("timeUntilExpiration"))
        builder.addPropertyValue("unitOfMeasurement", element.getAttribute("unitOfMeasurement"))
    }
}
