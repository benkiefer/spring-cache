package org.burgers.spring.cache.example

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.junit.Before
import org.burgers.spring.cache.example.storage.MyService
import org.burgers.spring.cache.example.storage.Listener

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
    void numberOfCharacters_performance_example(){
        myService.listener = countingListener
        def start = System.currentTimeMillis()
        25000.times{
            assert 4 == myService.dateCacheNumberOfCharacters("blah")
        }
        def end = System.currentTimeMillis() - start
        assert end < 1200
        println end
        assert countingListener.count == 1
    }

    @Test
    void standardCacheNumberOfCharacters_performance_example(){
        myService.listener = countingListener
        def start = System.currentTimeMillis()
        25000.times{
            assert 4 == myService.standardCacheNumberOfCharacters("blah")
        }
        def end = System.currentTimeMillis() - start
        assert end < 700
        println end
        assert countingListener.count == 1
    }

    @Test
    void alwaysExpiringNumberOfCharacters_performance_example(){
        myService.listener = countingListener
        def start = System.currentTimeMillis()
        25000.times{
            assert 4 == myService.alwaysExpiringDateCacheNumberOfCharacters("blah")
        }
        def end = System.currentTimeMillis() - start
        println end
        assert end < 2200
        assert countingListener.count == 25000
    }

    class CountingListener implements Listener {
        int count = 0
        void listen() { count++ }
    }

}
