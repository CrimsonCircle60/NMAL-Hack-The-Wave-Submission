import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.RenderingHints;


public class DrawingCanvas extends JComponent {
    private int width;
    private int height;

    public DrawingCanvas(int w, int h) {
        width = w;
        height = h;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double r = new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(new Color(100,149,237));
        g2d.fill(r);
    }

}