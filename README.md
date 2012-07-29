Spring 3.1 Spring Cache Example
======================

A schema on top of Spring's caching mechanism.

Includes an additional cache that will purge records after a configured amount of time, but you must call the appropriate clean method on the cache.

Ex:

    <caches:caching id="cacheManager">
        <caches:standard name="other"/>
        <caches:date-based-cache id="words" name="words"
                        timeUntilExpiration="${spring.cache.time.until.expiration}"
                        unitOfMeasurement="${spring.cache.unit.of.measurement}"/>
    </caches:caching>

Technologies used:
 - Groovy
 - Spring
 - Maven

This build requires that you:
 - Have maven set up. (For help, see: http://maven.apache.org/)