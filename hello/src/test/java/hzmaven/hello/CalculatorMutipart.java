package hzmaven.hello;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class CalculatorMutipart {
	private Calculator calculator;
	
	@Before
	public void setUp(){
		this.calculator=new Calculator();
		calculator.clear();
	}
	
	@Test
	public void testMultipart() {
		calculator.add(1);
		calculator.multiply(10);
		
		assertThat(calculator.getResult(),greaterThan(5));
		assertEquals(calculator.getResult(),10);
	}

}
