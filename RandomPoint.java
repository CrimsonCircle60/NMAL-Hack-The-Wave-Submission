import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;

public class RandomPoint implements WorldGenerator {

	@Override
	public int[][] createWorld(int width, int height) {
		int[][] map = new int[width][height];

		Random r = new Random();

		for(int x = 0; x<map.length; x++) {
			for(int y = 0; y < map[x].length; y++) {
				map[x][y] = r.nextInt(2); //Random value between 0 and 1;
			}
		}
		return map;
	}
}