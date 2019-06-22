package com.main.SimpleWeb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWebApplicationTests {

	@Test
	public void contextLoads() {
	}
        
        @Test
        public void basicTest(){
            System.out.println("Basic Test Pass!");
        }

}
