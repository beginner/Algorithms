import java.util.ArrayList;
import java.util.List;

public class ModifyTheMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {-1, 0, 0, 2, 2},
                {2, 0, 0, 2, 1},
                {4, 3, 2, 1, 1},
                {-1, -1, 0, 2, 4},
                {1, 0, 3, -1, 0}
        };
        ModifyTheMatrix problem = new ModifyTheMatrix();
        System.out.println(problem.modifiedMatrix(matrix));
    }



    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < n ; i++) { // column
            int max = -1;
            final List<Integer> missing = new ArrayList<>();
            int missingJ = -1;
            for (int j = 0; j < m; j++) { // row
                max = Math.max(max, matrix[j][i]);
                if (matrix[j][i] == -1) {
                    missing.add(j);
                }
            }
            for (int index : missing) {
                matrix[index][i] = max;
            }
        }
        return matrix;
    }
}
