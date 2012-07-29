package org.burgers.spring.cache.example.storage

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class MyServiceImpl implements MyService {
    Listener listener = new NoOpListener()

    @Cacheable("words")
    int numberOfCharacters(String evaluatedString) {
        listener.listen()

//        Pretend this is some expensive operation that you'd want to cache.
        evaluatedString.size();
    }

    class NoOpListener implements Listener {
        void listen() { }
    }
}
