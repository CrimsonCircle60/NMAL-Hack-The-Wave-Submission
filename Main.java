import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends javax.swing.JPanel implements ActionListener, MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = dim.getSize().width;
	public static final int height = dim.getSize().height;
	public static final int deltaTime = 200;

    public static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
    private Maze maze = new Maze();

    private int locationRow = Maze.linearSearch(maze.maze2, 2)[0];
    private int locationCol = Maze.linearSearch(maze.maze2, 2)[1];
    private int direction = 0; // 1 = Up, 2 = Left, 3 = Right, 4 = Down, initial number abitrary

    private static final Color tier3Color = new Color(50, 50, 50);
    private static final Color tier2Color = new Color(100, 100, 100);
    private static final Color tier1Color = new Color(150, 150, 150);

    public void paint(Graphics g) {

        super.paintComponent(g);

        if (keysPressed.contains(37) && keysPressed.contains(39));
        else if (keysPressed.contains(37)) {
            if (direction == 1) direction = 2;
            else if (direction == 2) direction = 4;
            else if (direction == 3) direction = 1;
            else if (direction == 4) direction = 3;
        } else if (keysPressed.contains(39)) {
            if (direction == 1) direction = 3;
            else if (direction == 2) direction = 1;
            else if (direction == 3) direction = 4;
            else if (direction == 4) direction = 2;
        }
        
        if (keysPressed.contains(38)) {
            if (direction == 1 && Maze.inBounds(locationRow - 1, locationCol, maze.maze2) && maze.maze2[locationRow - 1][locationCol] != 0) {
                locationRow -= 1;
            } else if (direction == 2 && Maze.inBounds(locationRow, locationCol - 1, maze.maze2) && maze.maze2[locationRow][locationCol - 1] != 0) {
                locationCol -= 1;
            } else if (direction == 3 && Maze.inBounds(locationRow, locationCol + 1, maze.maze2) && maze.maze2[locationRow][locationCol + 1] != 0) {
                locationCol += 1;
            } else if (direction == 4 && Maze.inBounds(locationRow + 1, locationCol, maze.maze2) && maze.maze2[locationRow + 1][locationCol] != 0) {
                locationRow += 1;
            }
        } else if (keysPressed.contains(40)) {
            if (direction == 1 && Maze.inBounds(locationRow + 1, locationCol, maze.maze2) && maze.maze2[locationRow + 1][locationCol] != 0) {
                locationRow += 1;
            } else if (direction == 2 && Maze.inBounds(locationRow, locationCol + 1, maze.maze2) && maze.maze2[locationRow][locationCol + 1] != 0) {
                locationCol += 1;
            } else if (direction == 3 && Maze.inBounds(locationRow, locationCol - 1, maze.maze2) && maze.maze2[locationRow][locationCol - 1] != 0) {
                locationCol -= 1;
            } else if (direction == 4 && Maze.inBounds(locationRow - 1, locationCol, maze.maze2) && maze.maze2[locationRow - 1][locationCol] != 0) {
                locationRow -= 1;
            }
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        

        if (direction == 4) {

            
            // Tier 3 Wierdos
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow + 3, locationCol - 3, maze.maze2) || maze.maze2[locationRow + 3][locationCol - 3] == 0) {
                int[] x = {12 * width / 13, width, width, 12 * width / 13, 12 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(width, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 3, locationCol - 2, maze.maze2) || maze.maze2[locationRow + 3][locationCol - 2] == 0) {
                int[] x = {9 * width / 13, 10 * width / 13, 10 * width / 13, 9 * width / 13, 9 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 3, locationCol + 3, maze.maze2) || maze.maze2[locationRow + 3][locationCol + 3] == 0) {
                int[] x = {0, 1 * width / 13, 1 * width / 13, 0, 0};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 3, locationCol + 2, maze.maze2) || maze.maze2[locationRow + 3][locationCol + 2] == 0) {
                int[] x = {3 * width / 13, 4 * width / 13, 4 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Wierdos
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow + 2, locationCol - 2, maze.maze2) || maze.maze2[locationRow + 2][locationCol - 2] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol + 2, maze.maze2) || maze.maze2[locationRow + 2][locationCol + 2] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            

            // Tier 3 Normals
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow + 3, locationCol, maze.maze2) || maze.maze2[locationRow + 3][locationCol] == 0) {
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
            } else if (Maze.inBounds(locationRow + 3, locationCol, maze.maze2) && maze.maze2[locationRow + 3][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            } else if (Maze.inBounds(locationRow + 3, locationCol, maze.maze2) && maze.maze2[locationRow + 3][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            }
            if (!Maze.inBounds(locationRow + 3, locationCol - 1, maze.maze2) || maze.maze2[locationRow + 3][locationCol - 1] == 0) {
                int[] x = {7 * width / 13, 8 * width / 13, 8 * width / 13, 7 * width / 13, 7 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
                
            }
            if (!Maze.inBounds(locationRow + 3, locationCol + 1, maze.maze2) || maze.maze2[locationRow + 3][locationCol + 1] == 0) {
                int[] x = {5 * width / 13, 6 * width / 13, 6 * width / 13, 5 * width / 13, 5 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(3 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Normals
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow + 2, locationCol, maze.maze2) || maze.maze2[locationRow + 2][locationCol] == 0) {
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
            } else if (Maze.inBounds(locationRow + 2, locationCol, maze.maze2) && maze.maze2[locationRow + 2][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            } else if (Maze.inBounds(locationRow + 2, locationCol, maze.maze2) && maze.maze2[locationRow + 2][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol - 1, maze.maze2) || maze.maze2[locationRow + 2][locationCol - 1] == 0) {
                int[] x = {8 * width / 13, 10 * width / 13, 10 * width / 13, 8 * width / 13, 8 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, height / 4, 3 * width / 13, height / 2);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol + 1, maze.maze2) || maze.maze2[locationRow + 2][locationCol + 1] == 0) {
                int[] x = {3 * width / 13, 5 * width / 13, 5 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, height / 4, 3 * width / 13, height / 2);
            }

            // Tier 1 Normals
            g.setColor(tier1Color);
            if (!Maze.inBounds(locationRow + 1, locationCol, maze.maze2) || maze.maze2[locationRow + 1][locationCol] == 0) {
                g.fillRect(0, 0, width, height);
            } else if (Maze.inBounds(locationRow + 1, locationCol, maze.maze2) && maze.maze2[locationRow + 1][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            } else if (Maze.inBounds(locationRow + 1, locationCol, maze.maze2) && maze.maze2[locationRow + 1][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol - 1, maze.maze2) || maze.maze2[locationRow + 1][locationCol - 1] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {height / 4, 0, height, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol + 1, maze.maze2) || maze.maze2[locationRow + 1][locationCol + 1] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {0, height / 4, 3 * height / 4, height, 0};
                g.fillPolygon(x, y, 5);
            }


        } else if (direction == 2) {


            // Tier 3 Wierdos
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow + 3, locationCol - 3, maze.maze2) || maze.maze2[locationRow + 3][locationCol - 3] == 0) {
                int[] x = {0, 1 * width / 13, 1 * width / 13, 0, 0};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol - 3, maze.maze2) || maze.maze2[locationRow + 2][locationCol - 3] == 0) {
                int[] x = {3 * width / 13, 4 * width / 13, 4 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 3, locationCol - 3, maze.maze2) || maze.maze2[locationRow - 3][locationCol - 3] == 0) {
                int[] x = {12 * width / 13, width, width, 12 * width / 13, 12 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(width, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol - 3, maze.maze2) || maze.maze2[locationRow - 2][locationCol - 3] == 0) {
                int[] x = {9 * width / 13, 10 * width / 13, 10 * width / 13, 9 * width / 13, 9 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Wierdos
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow + 2, locationCol - 2, maze.maze2) || maze.maze2[locationRow + 2][locationCol - 2] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol - 2, maze.maze2) || maze.maze2[locationRow - 2][locationCol - 2] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
            }
            

            // Tier 3 Normals
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow, locationCol - 3, maze.maze2) || maze.maze2[locationRow][locationCol - 3] == 0) {
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
            } else if (Maze.inBounds(locationRow, locationCol - 3, maze.maze2) && maze.maze2[locationRow][locationCol - 3] == 3) {
                g.setColor(Color.green);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            } else if (Maze.inBounds(locationRow, locationCol - 3, maze.maze2) && maze.maze2[locationRow][locationCol - 3] == 2) {
                g.setColor(Color.red);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol - 3, maze.maze2) || maze.maze2[locationRow + 1][locationCol - 3] == 0) {
                int[] x = {5 * width / 13, 6 * width / 13, 6 * width / 13, 5 * width / 13, 5 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(3 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol - 3, maze.maze2) || maze.maze2[locationRow - 1][locationCol - 3] == 0) {
                int[] x = {7 * width / 13, 8 * width / 13, 8 * width / 13, 7 * width / 13, 7 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Normals
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow, locationCol - 2, maze.maze2) || maze.maze2[locationRow][locationCol - 2] == 0) {
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
            } else if (Maze.inBounds(locationRow, locationCol - 2, maze.maze2) && maze.maze2[locationRow][locationCol - 2] == 3) {
                g.setColor(Color.green);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            } else if (Maze.inBounds(locationRow, locationCol - 2, maze.maze2) && maze.maze2[locationRow][locationCol - 2] == 2) {
                g.setColor(Color.red);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol - 2, maze.maze2) || maze.maze2[locationRow + 1][locationCol - 2] == 0) {
                int[] x = {3 * width / 13, 5 * width / 13, 5 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, height / 4, 3 * width / 13, height / 2);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol - 2, maze.maze2) || maze.maze2[locationRow - 1][locationCol - 2] == 0) {
                int[] x = {8 * width / 13, 10 * width / 13, 10 * width / 13, 8 * width / 13, 8 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, height / 4, 3 * width / 13, height / 2);
            }

            // Tier 1 Normals
            g.setColor(tier1Color);
            if (!Maze.inBounds(locationRow, locationCol - 1, maze.maze2) || maze.maze2[locationRow][locationCol - 1] == 0) {
                g.fillRect(0, 0, width, height);
            } else if (Maze.inBounds(locationRow, locationCol - 1, maze.maze2) && maze.maze2[locationRow][locationCol - 1] == 3) {
                g.setColor(Color.green);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            } else if (Maze.inBounds(locationRow, locationCol - 1, maze.maze2) && maze.maze2[locationRow][locationCol - 1] == 2) {
                g.setColor(Color.red);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol - 1, maze.maze2) || maze.maze2[locationRow + 1][locationCol - 1] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {0, height / 4, 3 * height / 4, height, 0};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol - 1, maze.maze2) || maze.maze2[locationRow - 1][locationCol - 1] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {height / 4, 0, height, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            

        } else if (direction == 1) {
            

            // Tier 3 Wierdos
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow - 3, locationCol + 3, maze.maze2) || maze.maze2[locationRow - 3][locationCol + 3] == 0) {
                int[] x = {12 * width / 13, width, width, 12 * width / 13, 12 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(width, 3 * height / 8, 2 * width / 13, height / 4);
                
            }
            if (!Maze.inBounds(locationRow - 3, locationCol + 2, maze.maze2) || maze.maze2[locationRow - 3][locationCol + 2] == 0) {
                int[] x = {9 * width / 13, 10 * width / 13, 10 * width / 13, 9 * width / 13, 9 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 3, locationCol - 3, maze.maze2) || maze.maze2[locationRow - 3][locationCol - 3] == 0) {
                int[] x = {0, 1 * width / 13, 1 * width / 13, 0, 0};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 3, locationCol - 2, maze.maze2) || maze.maze2[locationRow - 3][locationCol - 2] == 0) {
                int[] x = {3 * width / 13, 4 * width / 13, 4 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Wierdos
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow - 2, locationCol + 2, maze.maze2) || maze.maze2[locationRow - 2][locationCol + 2] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol - 2, maze.maze2) || maze.maze2[locationRow - 2][locationCol - 2] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            

            // Tier 3 Normals
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow - 3, locationCol, maze.maze2) || maze.maze2[locationRow - 3][locationCol] == 0) {
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
            } else if (Maze.inBounds(locationRow - 3, locationCol, maze.maze2) && maze.maze2[locationRow - 3][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            } else if (Maze.inBounds(locationRow - 3, locationCol, maze.maze2) && maze.maze2[locationRow - 3][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            }
            if (!Maze.inBounds(locationRow - 3, locationCol + 1, maze.maze2) || maze.maze2[locationRow - 3][locationCol + 1] == 0) {
                int[] x = {7 * width / 13, 8 * width / 13, 8 * width / 13, 7 * width / 13, 7 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 3, locationCol - 1, maze.maze2) || maze.maze2[locationRow - 3][locationCol - 1] == 0) {
                int[] x = {5 * width / 13, 6 * width / 13, 6 * width / 13, 5 * width / 13, 5 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(3 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Normals
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow - 2, locationCol, maze.maze2) || maze.maze2[locationRow - 2][locationCol] == 0) {
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
            } else if (Maze.inBounds(locationRow - 2, locationCol, maze.maze2) || maze.maze2[locationRow - 2][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            } else if (Maze.inBounds(locationRow - 2, locationCol, maze.maze2) || maze.maze2[locationRow - 2][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol + 1, maze.maze2) || maze.maze2[locationRow - 2][locationCol + 1] == 0) {
                int[] x = {8 * width / 13, 10 * width / 13, 10 * width / 13, 8 * width / 13, 8 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, height / 4, 3 * width / 13, height / 2);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol - 1, maze.maze2) || maze.maze2[locationRow - 2][locationCol - 1] == 0) {
                int[] x = {3 * width / 13, 5 * width / 13, 5 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, height / 4, 3 * width / 13, height / 2);
            }

            // Tier 1 Normals
            g.setColor(tier1Color);
            if (!Maze.inBounds(locationRow - 1, locationCol, maze.maze2) || maze.maze2[locationRow - 1][locationCol] == 0) {
                g.fillRect(0, 0, width, height);
            } else if (Maze.inBounds(locationRow - 1, locationCol, maze.maze2) && maze.maze2[locationRow - 1][locationCol] == 3) {
                g.setColor(Color.green);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            } else if (Maze.inBounds(locationRow - 1, locationCol, maze.maze2) && maze.maze2[locationRow - 1][locationCol] == 2) {
                g.setColor(Color.red);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol + 1, maze.maze2) || maze.maze2[locationRow - 1][locationCol + 1] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {height / 4, 0, height, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol - 1, maze.maze2) || maze.maze2[locationRow - 1][locationCol - 1] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {0, height / 4, 3 * height / 4, height, 0};
                g.fillPolygon(x, y, 5);
            }


        } else if (direction == 3) {
            

            // Tier 3 Wierdos
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow - 3, locationCol + 3, maze.maze2) || maze.maze2[locationRow - 3][locationCol + 3] == 0) {
                int[] x = {0, 1 * width / 13, 1 * width / 13, 0, 0};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow - 2, locationCol + 3, maze.maze2) || maze.maze2[locationRow - 2][locationCol + 3] == 0) {
                int[] x = {3 * width / 13, 4 * width / 13, 4 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 3, locationCol + 3, maze.maze2) || maze.maze2[locationRow + 3][locationCol + 3] == 0) {
                int[] x = {12 * width / 13, width, width, 12 * width / 13, 12 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(width, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol + 3, maze.maze2) || maze.maze2[locationRow + 2][locationCol + 3] == 0) {
                int[] x = {9 * width / 13, 10 * width / 13, 10 * width / 13, 9 * width / 13, 9 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Wierdos
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow - 2, locationCol + 2, maze.maze2) || maze.maze2[locationRow - 2][locationCol + 2] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow + 2, locationCol + 2, maze.maze2) || maze.maze2[locationRow + 2][locationCol + 2] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
            }
            

            // Tier 3 Normals
            g.setColor(tier3Color);
            if (!Maze.inBounds(locationRow, locationCol + 3, maze.maze2) || maze.maze2[locationRow][locationCol + 3] == 0) {
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
            } else if (Maze.inBounds(locationRow, locationCol + 3, maze.maze2) && maze.maze2[locationRow][locationCol + 3] == 3) {
                g.setColor(Color.green);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            } else if (Maze.inBounds(locationRow, locationCol + 3, maze.maze2) && maze.maze2[locationRow][locationCol + 3] == 2) {
                g.setColor(Color.red);
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
                g.setColor(tier3Color);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol + 3, maze.maze2) || maze.maze2[locationRow - 1][locationCol + 3] == 0) {
                int[] x = {5 * width / 13, 6 * width / 13, 6 * width / 13, 5 * width / 13, 5 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(3 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol + 3, maze.maze2) || maze.maze2[locationRow + 1][locationCol + 3] == 0) {
                int[] x = {7 * width / 13, 8 * width / 13, 8 * width / 13, 7 * width / 13, 7 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, 3 * height / 8, 2 * width / 13, height / 4);
            }

            // Tier 2 Normals
            g.setColor(tier2Color);
            if (!Maze.inBounds(locationRow, locationCol + 2, maze.maze2) || maze.maze2[locationRow][locationCol + 2] == 0) {
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
            } else if (Maze.inBounds(locationRow, locationCol + 2, maze.maze2) && maze.maze2[locationRow][locationCol + 2] == 3) {
                g.setColor(Color.green);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            } else if (Maze.inBounds(locationRow, locationCol + 2, maze.maze2) && maze.maze2[locationRow][locationCol + 2] == 2) {
                g.setColor(Color.red);
                g.fillRect(3 * width / 13, height / 4, 7 * width / 13, height / 2);
                g.setColor(tier2Color);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol + 2, maze.maze2) || maze.maze2[locationRow - 1][locationCol + 2] == 0) {
                int[] x = {3 * width / 13, 5 * width / 13, 5 * width / 13, 3 * width / 13, 3 * width / 13};
                int[] y = {height / 4, 3 * height / 8, 5 * height / 8, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
                g.fillRect(0, height / 4, 3 * width / 13, height / 2);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol + 2, maze.maze2) || maze.maze2[locationRow + 1][locationCol + 2] == 0) {
                int[] x = {8 * width / 13, 10 * width / 13, 10 * width / 13, 8 * width / 13, 8 * width / 13};
                int[] y = {3 * height / 8, height / 4, 3 * height / 4, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
                g.fillRect(10 * width / 13, height / 4, 3 * width / 13, height / 2);
            }

            // Tier 1 Normals
            g.setColor(tier1Color);
            if (!Maze.inBounds(locationRow, locationCol + 1, maze.maze2) || maze.maze2[locationRow][locationCol + 1] == 0) {
                g.fillRect(0, 0, width, height);
            } else if (Maze.inBounds(locationRow, locationCol + 1, maze.maze2) && maze.maze2[locationRow][locationCol + 1] == 3) {
                g.setColor(Color.green);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            } else if (Maze.inBounds(locationRow, locationCol + 1, maze.maze2) && maze.maze2[locationRow][locationCol + 1] == 2) {
                g.setColor(Color.red);
                g.fillRect(0, 0, width, height);
                g.setColor(tier1Color);
            }
            if (!Maze.inBounds(locationRow - 1, locationCol + 1, maze.maze2) || maze.maze2[locationRow - 1][locationCol + 1] == 0) {
                int[] x = {0, 3 * width / 13, 3 * width / 13, 0, 0};
                int[] y = {0, height / 4, 3 * height / 4, height, 0};
                g.fillPolygon(x, y, 5);
            }
            if (!Maze.inBounds(locationRow + 1, locationCol + 1, maze.maze2) || maze.maze2[locationRow + 1][locationCol + 1] == 0) {
                int[] x = {10 * width / 13, width, width, 10 * width / 13, 10 * width / 13};
                int[] y = {height / 4, 0, height, 3 * height / 4, height / 4};
                g.fillPolygon(x, y, 5);
            }

            
        }

            
        g.setColor(Color.white);
        if (direction == 1) g.drawString("Up", width / 2, height / 3);
        else if (direction == 2) g.drawString("Left", width / 2, height / 3);
        else if (direction == 3) g.drawString("Right", width / 2, height / 3);
        else if (direction == 4) g.drawString("Down", width / 2, height / 3);

        g.drawString("Row: " + locationRow, width / 4, 2 * height / 3);
        g.drawString("Col: " + locationCol, 3 * width / 4, 2 * height / 3);


    }

    public static void main(String[] args) {

        Main m = new Main();
        m.blank();

    }

    private void blank(){};

    public Main() {
        if (locationRow == 0) {
            direction = 4;
        } else if (locationRow == maze.maze2.length - 1) {
            direction = 1;
        } else if (locationCol == 0) {
            direction = 3;
        } else if (locationCol == maze.maze2.length - 1) {
            direction = 2;
        }
        JFrame f = new JFrame("3D Maze");
		f.setSize(width, height);
		f.setBackground(Color.black);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 1));
		f.addMouseListener(this);
		f.addKeyListener(this);
		f.setUndecorated(true);
		Timer t = new Timer(deltaTime, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Typed: " + e.getKeyCode());
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (!keysPressed.contains(e.getKeyCode())) keysPressed.add(e.getKeyCode());
        System.out.println("Pressed: " + e.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        for (int i = 0; i < keysPressed.size(); i++) {
			if (keysPressed.get(i) == e.getKeyCode()) {
				keysPressed.remove(i);
				break;
			}
		}
        System.out.println("Released: " + e.getKeyCode());
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

    

}