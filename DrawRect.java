import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;


public class DrawRect extends JPanel {
   private static final int RECT_X = 250;
   private static final int RECT_Y = RECT_X;
   private static final int RECT_WIDTH = 100;
   private static final int RECT_HEIGHT = RECT_WIDTH;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      // draw the rectangle here
      g2.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
      g2.setColor(Color.blue);
      
   }

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH + 2 * RECT_X, RECT_HEIGHT + 2 * RECT_Y);
   }

   // create the GUI explicitly on the Swing event thread
   private static void createAndShowGui() {
      DrawRect mainPanel = new DrawRect();

      JFrame frame = new JFrame("DrawRect");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}

public class DrawRect extends JPanel {
   private static final int RECT_X = 250;
   private static final int RECT_Y = RECT_X;
   private static final int RECT_WIDTH = 100;
   private static final int RECT_HEIGHT = RECT_WIDTH;

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      // draw the rectangle here
      g2.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
      g2.setColor(Color.blue);
      
   }

   @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH + 2 * RECT_X, RECT_HEIGHT + 2 * RECT_Y);
   }

   // create the GUI explicitly on the Swing event thread
   private static void createAndShowGui() {
      DrawRect mainPanel = new DrawRect();

      JFrame frame = new JFrame("DrawRect");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}