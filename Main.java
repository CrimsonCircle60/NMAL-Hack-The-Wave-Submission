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
	public static final int deltaTime = 250;

    public static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
    private Maze maze = new Maze();

    private int locationRow = 0; // to be changed to maze.linearSearch(maze.maze2, 2)[0];
    private int locationCol = 1; // to be changed to maze.linearSearch(maze.maze2, 2)[1];
    private int direction = 0; // 1 = Up, 2 = Left, 3 = Right, 4 = Down, initial number abitrary

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

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        
        if (direction == 4) {
            if (!maze.inBounds(locationRow + 3, locationCol, maze.maze2) || maze.maze2[locationRow + 3][locationCol] == 0) {
                g.setColor(new Color(50, 50, 50));
                g.fillRect(width/2 - 3 * width / 26, height / 2 - height / 8, 3 * width / 13, height / 4);
            }
            if (!maze.inBounds(locationRow + 3, locationCol - 1, maze.maze2) || maze.maze2[locationRow + 3][locationCol - 1] == 0) {
                g.setColor(new Color(50, 50, 50));
                int[] x = {5 * width / 13, 6 * width / 13, 6 * width / 13, 5 * width / 13, 5 * width / 13};
                int[] y = {3 * height / 8, 7 * height / 16, 9 * height / 16, 5 * height / 8, 3 * height / 8};
                g.fillPolygon(x, y, 5);
            }
            if (!maze.inBounds(locationRow + 3, locationCol + 1, maze.maze2) || maze.maze2[locationRow + 3][locationCol + 1] == 0) {
                g.setColor(new Color(50, 50, 50));
                int[] x = {7 * width / 13, 8 * width / 13, 7 * width / 13, 8 * width / 13, 7 * width / 13};
                int[] y = {7 * height / 16, 3 * height / 8, 5 * height / 8, 9 * height / 16, 7 * height / 16};
                g.fillPolygon(x, y, 5);
            }
        } else if (direction == 2) {

        } else if (direction == 3) {

        } else if (direction == 4) {

        }

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
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    

}