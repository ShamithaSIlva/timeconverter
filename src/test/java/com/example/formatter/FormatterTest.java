package com.example.formatter;
import org.junit.Assert;
import org.mockito.Mockito;

import com.example.formatter.Formatter;

import junit.framework.TestCase;

public class FormatterTest extends TestCase
{
	Formatter formatter = new Formatter();
	
	public void testIsValidString()
	{			
		assertTrue( formatter.isValidString( "[PM] 0:00.000" ) );
		assertTrue( formatter.isValidString( "[H1] 0:15.025" ) );
		assertTrue( formatter.isValidString( "[H1] 3:07.513" ) );
		assertTrue( formatter.isValidString( "[H1] 45:00.001" ) );
		assertTrue( formatter.isValidString( "[H1] 46:15.752" ) );
		assertTrue( formatter.isValidString( "[HT] 45:00.000" ) );
		assertTrue( formatter.isValidString( "[H2] 45:00.500" ) );
		assertTrue( formatter.isValidString( "[H2] 90:00.908" ) );
		assertTrue( formatter.isValidString( "[FT] 90:00.000" ) );
		assertTrue( !formatter.isValidString( "90:00" ) );
		assertTrue( !formatter.isValidString( "[H3] 90:00.000" ) );
		assertTrue( !formatter.isValidString( "[PM] -10:00.000" ) );
		assertTrue( !formatter.isValidString( "FOO" ) );
		
		// Additional tests
		assertTrue( !formatter.isValidString( "[FT] 99:00.999" ) ); // minutes cannot go beyond 90
		assertTrue( !formatter.isValidString( "[FT] 90:60.999" ) ); // seconds cannot go beyond 60
	}
	
	public void testConvertString()
	{
		Formatter formatter = Mockito.spy(new Formatter());
		Mockito.doReturn(true).when(formatter).isValidString("STRING_VALIDATION_PASSED"); 
	    Assert.assertEquals("00:00 – PRE_MATCH", formatter.convertString( "[PM] 0:00.000"));	
	    Assert.assertEquals("00:15 – FIRST_HALF", formatter.convertString( "[H1] 0:15.025"));	
	    Assert.assertEquals("03:08 – FIRST_HALF", formatter.convertString( "[H1] 3:07.513"));	
	    Assert.assertEquals("45:00 +00:00 – FIRST_HALF", formatter.convertString( "[H1] 45:00.001"));	
	    Assert.assertEquals("45:00 +01:16 – FIRST_HALF", formatter.convertString( "[H1] 46:15.752"));	
	    Assert.assertEquals("45:00 – HALF_TIME", formatter.convertString( "[HT] 45:00.000"));	
	    Assert.assertEquals("45:01 – SECOND_HALF", formatter.convertString( "[H2] 45:00.500"));	
	    Assert.assertEquals("90:00 +00:01 – SECOND_HALF", formatter.convertString( "[H2] 90:00.908"));	
	}

}
