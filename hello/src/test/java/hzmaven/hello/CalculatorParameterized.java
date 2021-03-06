package hzmaven.hello;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorParameterized {
	int expected;
	int input1;
	int input2;
	private Calculator calculator;
	
	@Parameters
	public static Collection<Object[]> hahaha(){
		return Arrays.asList(new Object[][]{{4,2,2},{3,1,3},{8,2,4}});
	}
	
	public CalculatorParameterized(int expected,int input1,int input2){
		this.expected=expected;
		this.input1=input1;
		this.input2=input2;
	}

	@Before
	public void setUp(){
		this.calculator=new Calculator();
		calculator.clear();
	}
	
	@Test
	public void testMutipart(){
		this.calculator.add(input1);
		this.calculator.multiply(input2);
		assertEquals((int)this.expected,this.calculator.getResult());
	}
}
