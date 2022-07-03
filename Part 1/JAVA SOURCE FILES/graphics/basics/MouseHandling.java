package graphics.basics;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.*;

public class MouseHandling 
{
    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                MouseHandling mh = new MouseHandling();
                MouseFrame mf = mh.new MouseFrame();
                mf.setVisible(true);
            }  
          });
    }

    public class MouseFrame extends JFrame
    {
        private static final int WIDTH = 400;
        private static final int HEIGHT = 400;

        public MouseFrame()
        {
            setSize(WIDTH, HEIGHT);

            setTitle("Square Dragger!");
            setLocationByPlatform(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            add(new MouseComponent());
        }
    }

    public class MouseComponent extends JComponent
    {
        private static final int SIDELENGTH = 10;
        private ArrayList<Rectangle2D> squares;
        private Rectangle2D current;

        public MouseComponent()
        {
            squares = new ArrayList<>();
            current = null;

            //add mouse listeners
            addMouseListener(new MouseHandler());
            addMouseMotionListener(new MouseMotionHandler());
        }

        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;

            for (Rectangle2D r : squares) {
                g2d.draw(r);
            }
        }

        //finds and returns the square containing specific point
        public Rectangle2D find(Point2D point)
        {
            for (Rectangle2D rectangle : squares) {
                if(rectangle.contains(point)) return rectangle;
            }
            return null;
        }

        //ads a square on a middle of click
        public void add(Point2D p)
        {
            double x = p.getX();
            double y = p.getY();

            current = new Rectangle2D.Double(x-SIDELENGTH/2, y-SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
            squares.add(current);
            repaint();
        }

        //removes square
        public void remove(Rectangle2D r)
        {
            if(r==null) return;
            if(r==current) current = null;
            squares.remove(r);
            repaint();
        }

        private class MouseHandler extends MouseAdapter
        {
            //adds a square if not on a square
            @Override
            public void mousePressed(MouseEvent event)
            {
                current = find(event.getPoint());
                if(current==null) add(event.getPoint());
            }
            
            //removes a square if on a square and doubleclick
            @Override
            public void mouseClicked(MouseEvent event)
            {
                current= find(event.getPoint());
                if(current!=null && event.getClickCount()>=2) remove(current);
            }
        }

        private class MouseMotionHandler implements MouseMotionListener
        {
            //sets cursor depending whether it is on a square
            public void mouseMoved(MouseEvent event)
            {
                if(find(event.getPoint())==null) 
                    setCursor(Cursor.getDefaultCursor());
                else 
                    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            //drags a square
            public void mouseDragged(MouseEvent event)
            {
                if(current!=null)
                {
                    int x = event.getX();
                    int y = event.getY();

                    current.setFrame(x-SIDELENGTH/2, y-SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
                    repaint();
                }
            }
        }
    }
    
}
