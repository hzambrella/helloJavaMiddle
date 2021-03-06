package hzmaven.hello;

import org.junit.*;

import static org.junit.Assert.*;

public class CaculatorTest {
	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		this.calculator = new Calculator();
		calculator.sayHello();
		calculator.clear();
	}
	
	@Ignore
	@Test
	public void testAdd() {
		calculator.add(1);
		calculator.add(1);
		assertEquals(calculator.getResult(), 2);
	}

	@Ignore
	@Test
	public void testSubtract() {
		calculator.add(10);
		calculator.substract(2);
		assertEquals(8,calculator.getResult());
	}

	@Ignore
	@Test
	public void testDivide() {
		calculator.add(8);
		calculator.divide(2);
		assertEquals(4,calculator.getResult());
	}

	@Ignore
	@Test
	public void testDivideByZero() {
		try {
			calculator.divide(0);
			fail();
		} catch (ArithmeticException e) {
		}
	}

	@Ignore
	@Test
	public void notReadyYetTestMultiply() {
		calculator.add(10);
		calculator.multiply(10);
		assertEquals(1000,calculator.getResult());
	}
}
