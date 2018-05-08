package ar.utn.tacs.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class AppTest 
    extends TestCase
{
	
    public AppTest()
    {
    	
    }

    @Test
    public void testApp()
    {
        assertTrue( true );
    }
}
