package graphics.basics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class SimpleFrame 
{
    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable() {
          public void run()
          {
            SimpleFrameTest frame = new SimpleFrameTest();
            frame.setVisible(true);
          }  
        });
    }

    static class HelloWorldCompenent extends JComponent
    {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;

        //private static final int MESS_X = 75;
        //private static final int MESS_Y = 100;

        public void paintComponent(Graphics g)
        {
            String mess ="Hello World!";
            Graphics2D g2d = (Graphics2D)g;

            Font f = new Font("Serif", Font.BOLD, 36);
            g2d.setFont(f);

            //check text size
            var frc = g2d.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(mess, frc);

            //this code should be copied and put in some function like center
            //it looks pretty tricky and weird
            //calculate the center
            double ascent = -bounds.getY();

            int x = (int)(getWidth() - bounds.getWidth())/2;
            int y = (int)(((getHeight() - bounds.getHeight())/2)+ascent);

            g2d.setColor(Color.RED);
            g2d.drawString(mess, x, y);
        }

        public Dimension getPreferredSize()
        {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }

    }

    static class GeometryComponent extends JComponent
    {
        private static final int DEFAULT_WIDTH = 400;
        private static final int DEFAULT_HEIGHT = 400;

        public void paintComponent(Graphics g)
        {
            int coordX = 100, coordY =100;
            int width = 200, height = 150;

            Graphics2D g2d = (Graphics2D)g;

            Rectangle2D rect = new Rectangle2D.Double(coordX, coordY, width, height);
            Ellipse2D elipse = new Ellipse2D.Double();
            elipse.setFrame(rect);

            Line2D line = new Line2D.Double(coordX, coordY, coordX+ width, coordY+height);

            Ellipse2D circle = new Ellipse2D.Double();
            circle.setFrameFromCenter(rect.getCenterX(), rect.getCenterY(), rect.getCenterX()+150, rect.getCenterY()+150);

            g2d.setPaint(Color.pink);

            g2d.draw(rect);
            g2d.draw(elipse);
            g2d.draw(line);
            g2d.draw(circle);
        }   

        public Dimension getPreferredSize()
        {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }

    static class ImageComponent extends JComponent
    {
        private static final int DEFAULT_WIDTH = 400;
        private static final int DEFAULT_HEIGHT = 400;

        private static final int IMAGE_WIDTH = 100;
        private static final int IMAGE_HEIGHT = 100;

        private Image image;

        public ImageComponent()
        {
            java.net.URL imageURL = SimpleFrame.class.getResource("mammon.png");
            image = new ImageIcon(imageURL).getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
        }

        public void paintComponent(Graphics g)
        {
            g.drawImage(image, 0, 0, null);

            //otherwise image wont appear until user wont resize window, idk why this happens, repaint seems to be bad idea to use here
            repaint();
        } 

        public Dimension getPreferredSize()
        {
            return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }

    //this should be called simpleframe but i made a little mistake
    static class SimpleFrameTest extends JFrame
    {
        //private static final int DEFAULT_WIDTH = 300;
        //private static final int DEFAULT_HEIGHT = 200;

        public SimpleFrameTest()
        {
            setTitle("Simple Frame Test");
            setLocationByPlatform(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            java.net.URL imgURL = SimpleFrame.class.getResource("mammon.png");
            setIconImage(new ImageIcon(imgURL).getImage());

            //components
            //add(new HelloWorldCompenent());
            //add(new GeometryComponent());
            add(new ImageComponent());
            getContentPane().setBackground(Color.BLACK);

            //pack
            pack();
        }
    }
}
