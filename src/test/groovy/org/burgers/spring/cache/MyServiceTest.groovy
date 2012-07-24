package org.burgers.spring.cache

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.junit.Before

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

    class CountingListener implements Listener {
        int count = 0
        void listen() { count++ }
    }

}
