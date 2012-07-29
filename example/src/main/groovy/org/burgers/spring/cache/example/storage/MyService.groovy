package org.burgers.spring.cache.example.storage

public interface MyService {
    int dateCacheNumberOfCharacters(String evaluatedString)
    int alwaysExpiringDateCacheNumberOfCharacters(String evaluatedString)
    int standardCacheNumberOfCharacters(String evaluatedString)

}