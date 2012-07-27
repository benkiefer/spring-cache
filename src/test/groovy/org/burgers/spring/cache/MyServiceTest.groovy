package org.burgers.spring.cache

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.junit.Before
import org.burgers.spring.cache.example.storage.Listener
import org.burgers.spring.cache.example.storage.MyService

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = "classpath:contexts/ApplicationContext.xml")
class MyServiceTest {
    @Autowired MyService myService
    CountingListener countingListener

    @Before
    void setUp(){
        countingListener = new CountingListener()
        myService.listener  = countingListener
    }

    @Test
    void numberOfCharacters(){
        assert 4 == myService.numberOfCharacters("blah")
        assert countingListener.count == 1
        assert 4 == myService.numberOfCharacters("blah")
        assert countingListener.count == 1
    }

    @Test
    void numberOfCharacters_performance_example(){
        myService.listener = new NappingListener()
        def start = System.currentTimeMillis()
        25000.times{
            myService.numberOfCharacters("blah")
        }
        def end = System.currentTimeMillis() - start
        assert end < 2000
    }


    class CountingListener implements Listener {
        int count = 0
        void listen() { count++ }
    }

    class NappingListener implements Listener {
        void listen(){Thread.sleep(1000)}
    }

}
