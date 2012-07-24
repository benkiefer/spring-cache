package org.burgers.spring.cache

import org.springframework.stereotype.Component

@Component
class MyServiceImpl implements MyService {
    Listener listener = new NoOpListener()

    @Override
    int numberOfCharacters(String evaluatedString) {
        listener.listen()

//        pretend this is some expensive operation that you'd want to cache.
        evaluatedString.size();
    }

    class NoOpListener implements Listener {
        void listen() { }
    }
}
