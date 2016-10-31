/**
 * @author Anton Solovev
 * @since 11/14/2016.
 */
public class MatrixCalculation {

    public int determinantMatrix(int[][] matrix) {
        int calcResult = 0;
        if (matrix.length == 2) {
            calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            int factor;
            for (int i = 0; i < matrix.length; i++) {
                if (i % 2 == 1) {
                    factor = -1;
                } else {
                    factor = 1;
                }
                calcResult += factor * matrix[0][i] * determinantMatrix(getMinor(matrix, 0, i));
            }
        }
        return calcResult;
    }

    private int[][] getMinor(int[][] matrix, int row, int column) {
        int minorLength = matrix.length - 1;
        int[][] minor = new int[minorLength][minorLength];
        int dI = 0;
        for (int i = 0; i <= minorLength; i++) {
            int dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor[i - dI][j - dJ] = matrix[i][j];
                    }
                }
            }
        }
        return minor;
    }
}
