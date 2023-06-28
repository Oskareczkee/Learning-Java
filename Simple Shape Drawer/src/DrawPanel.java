import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel
{
    private List<Point> points = new ArrayList<>();
    private BufferedImage canvas;

    //position of Panel's 0,0 coordinates, we want them on the middle of screen
    private int x0, y0;
    public void setPoints(List<Point> p)
    {
        points = p;
    }

    private int Width = 720;
    private int Height =480;
    public DrawPanel()
    {
        this.setPreferredSize(new Dimension(Width, Height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setDoubleBuffered(true);

        canvas = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);

        //set 0,0 in the middle of the panel
        x0 = Width/2;
        y0= Height/2;
    }

    private void createImage()
    {
        Graphics g = canvas.getGraphics();
        g.setColor(Color.WHITE);

        int pointCount = points.size();
        for(int x =0; x< pointCount;x++)
        {
            Point p1 = points.get(x);
            Point p2 = points.get((x+1) % pointCount);

            //draw from the beginning of out coord system
            g.drawLine(p1.x + x0, p1.y + y0, p2.x +x0, p2.y +y0);
        }

        g.dispose();
    }

    public void draw()
    {
        createImage();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, 0,0,null);
    }
}
