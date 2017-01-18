import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Anton Solovev
 * @since 31.10.16.
 */
public class RecursiveFunTest {

    @Test
    public void factorial() throws Exception {
        RecursiveFun fun = new RecursiveFun();
        int result = fun.factorial(5);
        assertThat(result, is(120));
    }

    @Test(expected = RuntimeException.class)
    public void factorialException() throws Exception {
        RecursiveFun fun = new RecursiveFun();
        int result = fun.factorial(21);
    }

}