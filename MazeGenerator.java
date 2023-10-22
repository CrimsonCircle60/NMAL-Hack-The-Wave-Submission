import java.util.*;

public class MazeGenerator {
	private static final int WALL = 0;
	private static final int EMPTY = 1;
	private static final int START = 2;
	private static final int END = 3;
	private static final int[] DX = {0, 0, 1, -1};
	private static final int[] DY = {1, -1, 0, 0};
	private final int n, m;
	private final int[][] maze;
	private final Random random;

	public MazeGenerator(int n, int m, int[] start, int[] end) {
		this.n = n;
		this.m = m;
		this.maze = new int[n][m];
		this.random = new Random();

		// Initialize maze with walls
		for (int i = 0; i < n; i++) {
			Arrays.fill(maze[i], WALL);
		}

		// Set start and end points
		maze[start[0]][start[1]] = START;
		maze[end[0]][end[1]] = END;

		// Generate maze
		dfs(start[0], start[1]);

		// Print maze (for debugging purposes)
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(maze[i]));
		}
	}

	private void dfs(int x, int y) {
		List<Integer> directions = Arrays.asList(0, 1, 2, 3);
		Collections.shuffle(directions, random);

		for (int d : directions) {
			int nx = x + DX[d];
			int ny = y + DY[d];

			if (nx < 0 || nx >= n || ny < 0 || ny >= m || maze[nx][ny] != WALL) {
				continue;
			}

			// Carve path
			maze[x + DX[d] / 2][y + DY[d] / 2] = EMPTY;
			maze[nx][ny] = EMPTY;

			// Recursively visit next cell
			dfs(nx, ny);
		}
	}

	public static void main(String[] args) {
		int n = 5, m = 5;
		int[] start = {0, 0}, end = {4, 4};
		MazeGenerator maze = new MazeGenerator(n, m, start, end);
	}
}
