import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anton Solovev
 * @since 11/17/2016.
 */
public class MatrixCalculationTest {

    private final int matrixSize = 7;
    private final int[][] testMatrix = testMatrix(matrixSize);
    private final int expected = 537824;

    private int[][] testMatrix(int matrixSize) {
        int[][] test = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                test[i][j] = (i * j + 1) % 14;
            }
        }
        test[0][0] = 15;
        return test;
    }

    @Test
    public void determinantMatrix() throws Exception {
        MatrixCalculation calc = new MatrixCalculation();
        int result = calc.determinantMatrix(testMatrix);
        assertThat(result, is(expected));
    }
}