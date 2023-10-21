public class Array {
    public static void main(String[] args) {
        int[][] map = {
            {0, 2, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 3},
            {0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
        };
        for(int i=0; i < map.length; i++) {
           
            for(int j=0; j < map[i].length; j++) {
                System.out.print(map[i][j] + ' ');
            } 
        }
    }
}

