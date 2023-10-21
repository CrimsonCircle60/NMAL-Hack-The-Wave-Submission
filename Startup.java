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

public class Startup {

	public static void main(String[] args) {
		WorldGenerator worldgen = new RandomPoint();
		MapImage mi = new MapImage();

		for (int i = 0; i < 5; i++) {
			int[][] array = worldgen.createWorld(100, 100);
			mi.visualize(array, "generatedMap" + i);
		}

	}
}
