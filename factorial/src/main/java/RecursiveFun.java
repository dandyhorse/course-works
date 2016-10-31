/**
 * @author Anton Solovev
 * @since 30.10.16.
 */
public class RecursiveFun {

    public int factorial(int in) {
        if (in >= 20) {
            throw new RuntimeException("operand must be less than 20");
        }
        if (in == 1) {
            return 1;
        } else {
            return (in * factorial(in - 1));
        }
    }
}
