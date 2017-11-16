package hzmaven.hello;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class CalculatetorAdd {
	private Calculator calculator;
	
	@Before
	public void setUp(){
		this.calculator=new Calculator();
		calculator.clear();
	}

	@Test
	public void testAdd() {
		calculator.add(1);
		calculator.add(1);
		assertEquals(calculator.getResult(),2);
	}

}
