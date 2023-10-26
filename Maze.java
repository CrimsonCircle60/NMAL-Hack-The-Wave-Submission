

public class Maze {
    
    public int maze2[][] = {
                    { 0, 2, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 3 },
                    { 0, 1, 1, 1, 1, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0 } };

    public int[][] randomMaze;

    public Maze() {

    }

    public Maze(int row, int col) {

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

    public static boolean inBounds(int row, int col, int[][] arr) {

        if (row < 0) return false;
        if (row >= arr.length) return false;
        if (col < 0) return false;
        if (col >= arr[0].length) return false;

        return true;

    }

}


