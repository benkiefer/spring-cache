package org.burgers.spring.cache.namespace.util

import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.w3c.dom.Element

class BeanIdCalculator {
    String calculate(String defaultId, Element element, BeanDefinitionRegistry registry){
        String beanId = defaultId

        String id = element.getAttribute("id")

        if (id) {
            beanId = id
        }

        if (registry.containsBeanDefinition(beanId)) {
            beanId = beanId + "_${System.currentTimeMillis()}"
        }

        beanId

    }
}
