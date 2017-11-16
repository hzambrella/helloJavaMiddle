package hzmaven.hello;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatetorAdd.class, CalculatorMutipart.class,CalculatorParameterized.class})
public class CalculatorAllTest {
	
}
