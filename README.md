##Spring 3.1 Spring Cache Enhancements

**Features**
 - A schema wrapper on top of SpringCache.
 - Includes EntryDateTrackingCache, an additional cache that will remove an item from the cache if it is expired.

**Note:** Unit of measurement is equivalent to the int value of the appropriate unit in java's Calendar class. Ex: 12 = MINUTE.

##Usage Examples

**Example 1:**
Use this if the ConcurrentMapCache and EntryDateTrackingCache are sufficient for your needs.

    <caches:caching id="cacheManager">
        <caches:standard name="things"/>
        <caches:date-based-cache name="otherThings"
                        timeUntilExpiration="5"
                        unitOfMeasurement="12"/>
    </caches:caching>

**Example 2:**
Use this if you want to create your own cache and then reference it by id in the schema.

    <caches:standard id="cache1" name="things"/>

    <caches:caching id="cacheManager">
        <caches:cache-ref ref="cache1"/>
        <caches:date-based-cache name="otherThings"
                        timeUntilExpiration="5"
                        unitOfMeasurement="12"/>
    </caches:caching>

