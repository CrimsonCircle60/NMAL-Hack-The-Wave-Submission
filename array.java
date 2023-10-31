import java.util.Arrays;
 
public class Array {
    public static void main(String[] args) {
        int arr[][] = { { 0, 2, 0, 0, 0, 0, 0 },
                        { 0, 1, 0, 0, 1, 1, 3 },
                        { 0, 1, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0 } };
        int target = 2;
        int ans[] = linearSearch(arr, target);
        System.out.println("Element found at index: "
                           + Arrays.toString(ans));
    }
 
    public static int[] linearSearch(int[][] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { -1, -1 };
    }
    
    public static boolean inBounds(int row, int col, int[][] array) {

        if (row < 0 || col < 0) return false;
        if (array.length - 1 < row || array[0].length - 1 < col) return false;

        return true;

    }
}