import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Anton Solovev
 * @since 25.10.16.
 */
public class CalculatorTest {
	private Calculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	@Test
	public void calculateTest() throws Exception {
		int result1 = calculator.calculate("5 + 2 * 4 - 7");
		int result2 = calculator.calculate("2 * 2 + 2 / 2");
		assertThat(result1, equalTo(6));
		assertThat(result2, equalTo(5));
	}

	@Test
	public void compileTest() throws Exception {
		String result = calculator.compile("5 + 2 * 4 - 7");
		assertThat(result, equalTo("5 2 4 * + 7 -"));
	}

	@Test
	public void postfixEvaluateTest() throws Exception {
		int result = calculator.postfixEvaluate("5 2 4 * + 7 -");
		assertThat(result, equalTo(6));
	}

}