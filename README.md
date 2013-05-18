##Spring 3.1 Spring Cache Enhancements

**Features**
 - A schema wrapper on top of SpringCache.
 - Includes EntryDateTrackingCache, an additional cache that will remove an item from the cache if it is expired.
 - Supported expiration values include: SECONDS, MINUTES, HOURS

[![Build Status](https://secure.travis-ci.org/benkiefer/spring-cache.png?branch=master)](http://travis-ci.org/benkiefer/spring-cache)

##Usage Examples

**Example 1:**
Use this if the ConcurrentMapCache and EntryDateTrackingCache are sufficient for your needs.

    <caches:caching id="cacheManager">
        <caches:standard name="things"/>
        <caches:date-based-cache name="otherThings"
                        timeUntilExpiration="5"
                        unitOfMeasurement="MINUTES"/>
    </caches:caching>

**Example 2:**
Use this if you want to create your own cache and then reference it by id in the schema.

    <bean id="cache1" class="org.springframework.cache.concurrent.ConcurrentMapCache">
        <constructor-arg index="0" value="things"/>
    </bean>

    <caches:caching id="cacheManager">
        <caches:cache-ref ref="cache1"/>
        <caches:date-based-cache name="otherThings"
                        timeUntilExpiration="5"
                        unitOfMeasurement="MINUTES"/>
    </caches:caching>

