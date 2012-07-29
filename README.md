##Spring 3.1 Spring Cache Enhancements

A schema wrapper on top of SpringCache that includes an additional cache that will purge records after a configured amount of time.

In order to purge expired records from the EntryDateTrackingCache, you must call the appropriate clearExpiredRecords on the cache.

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

